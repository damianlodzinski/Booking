package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarsVehicleDTO {
    private String vehicleClass;
    private String name;
    private String rateReference;
    private RentalCarsPriceParams price;
    private String status;
}
