package com.jfl.car_api.repository;

import com.jfl.car_api.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Integer> {
    Optional<Car> findByUuid(String uuid);
    boolean existsByUuid(String uuid);
    void deleteByUuid(String uuid);
}
