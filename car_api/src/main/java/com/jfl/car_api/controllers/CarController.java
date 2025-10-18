package com.jfl.car_api.controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfl.car_api.car.Car;
import com.jfl.car_api.car.CarDTO;
import com.jfl.car_api.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/cars")
    public List<CarDTO>  retrieveAllCars(){
        return carService.findAll();
    }

    @GetMapping("/cars/{uuid}")
    public CarDTO  retrieveCar(@PathVariable String uuid) {
        return CarDTO.from(carService.getByUuid(uuid));
    }

    @PostMapping("/cars")
    public ResponseEntity<CarDTO> createCar(@RequestBody Car car){
        Car savedCar = carService.save(car);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(car.getUuid())
                .toUri();

        return ResponseEntity.created(location).body(CarDTO.from(savedCar));
    }

    @PutMapping("/cars/{uuid}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable String uuid, @Valid @RequestBody CarDTO carDto) throws JsonMappingException {
        Car existingCar = carService.getByUuid(uuid);
        Integer originalId = existingCar.getId();
        String originalUuid = existingCar.getUuid();
        Instant createdAt = existingCar.getCreatedAt();

        Car updateData = objectMapper.convertValue(carDto,Car.class);

        objectMapper.updateValue(existingCar,updateData);

        existingCar.setId(originalId);
        existingCar.setUuid(originalUuid);
        existingCar.setCreatedAt(createdAt);

        Car updatedCar = carService.save(existingCar);

        URI self = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
        return ResponseEntity.ok()
                .location(self)
                .body(CarDTO.from(updatedCar));
    }

    @PatchMapping("/cars/{uuid}")
    public ResponseEntity<CarDTO> patchCar(
            @PathVariable String uuid,
            @Valid @RequestBody CarDTO carDto) {

        Car updated = carService.patchByUuid(uuid, carDto);

        URI self = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .build().toUri();

        return ResponseEntity.ok()
                .location(self)
                .body(CarDTO.from(updated));
    }

    @DeleteMapping("/cars/{uuid}")
    public ResponseEntity<Void> deleteCar(@PathVariable String uuid) {
        Car deleteCar = carService.getByUuid(uuid);
        if (deleteCar == null) {
            return ResponseEntity.notFound().build();
        }

        carService.delete(deleteCar.getUuid());
        return ResponseEntity.noContent().build();
    }

}
