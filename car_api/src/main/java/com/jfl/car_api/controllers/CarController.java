package com.jfl.car_api.controllers;

import com.jfl.car_api.car.CarDTO;
import com.jfl.car_api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jfl.car_api.car.Car;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public List<CarDTO>  retrieveAllCars(){
        return carService.findAll();
    }

    @GetMapping("/cars/{uuid}")
    public CarDTO  retrieveCar(@PathVariable String uuid) {
        return CarDTO.from(carService.getByUuid(uuid));
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        Car savedCar = carService.save(car);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(savedCar.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
