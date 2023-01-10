package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarsReservationVehicleParams {
    private String vehicleClass;
    private RentalCarsPriceParams price;
}
