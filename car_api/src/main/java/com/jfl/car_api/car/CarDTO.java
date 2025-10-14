package com.jfl.car_api.car;

import java.math.BigDecimal;

public record CarDTO(
        String uuid,
        String brand,
        String model,
        String color,
        Integer year,
        BigDecimal price,
        Integer km
) {}
