package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCarsEditReservationParams {
    private ArrayList<RentalCarsDriverParams> drivers;
    private RentalCarsReservationVehicleParams vehicle;
    private RentalCarsRateDTO rate;
    private ArrayList<RentalCarsEquipmentDTO> extras;
    private String pickUpDate;
    private String supplierPickUpLocCode;
    private String dropOffDate;
    private String supplierDropOffLocCode;
    private String rentalcarsReservationReference;
    private String transactionId;
    private String supplierReservationReference;
}
