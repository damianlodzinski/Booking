package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarsSearchRentalOffersResult {
    private String supplierReservationReference;
    private String rentalcarsReservationReference;
    private String pickUpDate;
    private String dropOffDate;
    private String supplierPickUpLocCode;
    private String supplierDropOffLocCode;
    private RentalCarsRateDTO rate;
    private ArrayList<RentalCarsOfferDTO> vehicles;
}
