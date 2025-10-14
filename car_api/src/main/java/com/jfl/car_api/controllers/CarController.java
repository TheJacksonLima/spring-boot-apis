package com.jfl.car_api.controllers;

import com.jfl.car_api.car.CarDTO;
import com.jfl.car_api.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jfl.car_api.car.Car;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/cars")
    public List<CarDTO>  retrieveAllCars(){
        return carRepository.findAll()
                .stream()
                .map(car -> new CarDTO(
                        car.getUuid(),
                        car.getBrand(),
                        car.getModel(),
                        car.getColor(),
                        car.getYear(),
                        car.getPrice(),
                        car.getKm()
                )).toList();
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        Car savedCar = carRepository.save(car);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(savedCar.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
