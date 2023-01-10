package com.example.Booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModel {
    private String carClassAlias;
    private String carClass;
    private String exampleCarPhoto;
    private String fullModelName;
    private int fuelType;
    private int maxPassengersAmount;
}
