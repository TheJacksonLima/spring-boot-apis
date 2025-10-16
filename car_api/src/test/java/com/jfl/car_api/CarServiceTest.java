package com.jfl.car_api;

import com.jfl.car_api.car.Car;
import com.jfl.car_api.repository.CarRepository;
import com.jfl.car_api.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;

    @Test
    void shouldSaveCarSuccessfully(){
        Car car = new Car();
        car.setBrand("Honda");
        car.setModel("Civic");

        when(carRepository.save(any(Car.class))).thenAnswer( i-> {
            Car c = i.getArgument(0);
            c.setId(1);
            return c;
        });

        Car saved = carService.save(car);

        verify(carRepository).save(car);
        assertEquals(1,saved.getId());
        assertEquals("Honda",saved.getBrand());
    }
}
