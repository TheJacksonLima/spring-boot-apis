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
)
{
   public static CarDTO from(Car car) {
       return new CarDTO(
               car.getUuid(),
               car.getBrand(),
               car.getModel(),
               car.getColor(),
               car.getYear(),
               car.getPrice(),
               car.getKm()
     );
   }
}
