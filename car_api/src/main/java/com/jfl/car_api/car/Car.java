package com.jfl.car_api.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity(name= "car_details")
@Data
public class Car {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, updatable = false,nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @PrePersist
    public void ensureUuid() {
        if (uuid == null) {
            uuid = java.util.UUID.randomUUID().toString();
        }
    }

    @Size(min=1, message = "Brand should have at least 1 characters")
    private String brand;

    @Size(min=2, message = "Model should have at least 1 characters")
    private String model;

    private String color;

    private Integer year;

    private BigDecimal price;

    private Integer km;

    @JsonIgnore
    private Instant createdAt;

    @JsonIgnore
    private Instant updatedAt;

}
