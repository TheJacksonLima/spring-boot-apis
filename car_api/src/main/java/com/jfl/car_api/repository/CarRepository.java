package com.jfl.car_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jfl.car_api.car.Car;

public interface CarRepository  extends JpaRepository<Car,Integer> {}
