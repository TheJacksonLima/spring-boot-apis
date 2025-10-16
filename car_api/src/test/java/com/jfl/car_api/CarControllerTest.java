package com.jfl.car_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfl.car_api.car.Car;
import com.jfl.car_api.car.CarDTO;
import com.jfl.car_api.controllers.CarController;
import com.jfl.car_api.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCars_shouldReturnList() throws Exception{
        Car car = new Car();

        car.setId(1);
        car.setBrand("Honda");
        car.setModel("Civic");

        when(carService.findAll()).thenReturn(List.of(CarDTO.from(car)));

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].brand").value("Honda"))
                .andExpect(jsonPath("$[0].model").value("Civic"));

    }

    @Test
    void postCar_shouldReturn201() throws Exception {
        Car input = new Car();
        input.setBrand("Toyota");
        input.setModel("Corolla");

        Car carSaved = new Car();
        carSaved.setUuid(String.valueOf(10L));
        carSaved.setBrand("Toyota");
        carSaved.setModel("Corolla");

        when(carService.save(any(Car.class))).thenReturn(carSaved);

        mockMvc.perform(post("/cars")
                    .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                    .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").value(String.valueOf(10L)))
                .andExpect(jsonPath("$.brand").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Corolla"));

    }



}
