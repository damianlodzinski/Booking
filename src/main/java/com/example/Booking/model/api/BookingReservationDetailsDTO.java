package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingReservationDetailsDTO {
    private ArrayList<BookingCarUserDTOer> drivers;
    private String supplierReservationReference;
    private String rentalcarsReservationReference;
    private String pickUpDate;
    private String supplierPickUpLocCode;
    private String dropOffDate;
    private String supplierDropOffLocCode;
    private RentalCarsRateDTO rate;
    private RentalCarsVehicleDTO vehicle;
    private ArrayList<RentalCarsEquipmentDTO> extras;
    private ArrayList<RentalCarsExtraFeeDTO> fees;
}
