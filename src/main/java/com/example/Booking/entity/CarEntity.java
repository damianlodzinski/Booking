package com.example.Booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class CarEntity {
    @Id
    private String carClassAlias;
    private String carClass;
    private String exampleCarPhoto;
    private String fullModelName;
    private int fuelType;
    private int maxPassengersAmount;
}
