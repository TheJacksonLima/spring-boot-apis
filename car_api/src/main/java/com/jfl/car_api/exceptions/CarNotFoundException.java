package com.jfl.car_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(Integer id) {
        super("Car not found with ID: " + id);
    }

    public CarNotFoundException(String uuid) {
        super("Car not found with UUID: " + uuid);
    }
}
