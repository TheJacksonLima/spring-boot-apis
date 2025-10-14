package com.jfl.car_api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarApiApplication {

	public static void main(String[] args) {
		Dotenv dotEnv = Dotenv.load();
		dotEnv.entries().forEach(entry -> System.setProperty(entry.getKey(),entry.getValue()));

		SpringApplication.run(CarApiApplication.class, args);
	}

}
