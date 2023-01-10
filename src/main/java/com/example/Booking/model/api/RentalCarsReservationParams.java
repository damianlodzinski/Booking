package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarsReservationParams {
    private ArrayList<RentalCarsDriverParams> drivers;
    private RentalCarsReservationVehicleParams vehicle;
    private RentalCarsRateDTO rate;
    private ArrayList<Object> extras;
    private String pickUpDate;
    private String supplierPickUpLocCode;
    private String dropOffDate;
    private String supplierDropOffLocCode;
    private String rentalcarsReservationReference;
    private String transactionId;
}
