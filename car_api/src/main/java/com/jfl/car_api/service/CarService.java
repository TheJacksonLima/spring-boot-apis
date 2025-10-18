package com.jfl.car_api.service;

import com.jfl.car_api.car.Car;
import com.jfl.car_api.car.CarDTO;
import com.jfl.car_api.exceptions.CarNotFoundException;
import com.jfl.car_api.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Car getById(Integer id){
        return carRepository.findById(id)
                .orElseThrow(
                    ()-> new CarNotFoundException(id)
                );
    }

    public Car getByUuid(String uuid) throws CarNotFoundException{
        return carRepository.findByUuid(uuid)
                .orElseThrow(
                        ()-> new CarNotFoundException(uuid)
                );
    }

    public List<CarDTO>  findAll() {
        return carRepository.findAll()
                .stream()
                .map(CarDTO::from)
                .toList();
    }

    public Car save(Car car) {
        car.setUpdatedAt(Instant.now());
        return carRepository.save(car);
    }

    @Transactional
    public void delete(String uuid) {
        carRepository.deleteByUuid(uuid);
    }

    @Transactional
    public Car patchByUuid(String uuid, CarDTO carDto){
        Car car = getByUuid(uuid);

        if (carDto.brand() != null)  car.setBrand(carDto.brand());
        if (carDto.model() != null)  car.setModel(carDto.model());
        if (carDto.color() != null)  car.setColor(carDto.color());
        if (carDto.year()  != null)  car.setYear(carDto.year());
        if (carDto.price() != null)  car.setPrice(carDto.price());
        if (carDto.km()    != null)  car.setKm(carDto.km());

        return save(car);
    }

}
