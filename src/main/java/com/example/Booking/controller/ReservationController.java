package com.example.Booking.controller;

import com.example.Booking.model.api.*;
import com.example.Booking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/{id}")
    public ResponseEntity<BookingReservationDetailsDTO> getReservationById(@PathVariable("id") int reservationId) {
        return new ResponseEntity<>(reservationService.getReservationById(reservationId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RentalCarsCancelReservationResult> deleteReservationById(
            @PathVariable("id") int reservationId) {
        return new ResponseEntity<>(reservationService.deleteReservationById(reservationId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RentalCarsEditReservationParams> editReservationById(
            @RequestBody RentalCarsEditReservationParams editReservationParams) {
        return new ResponseEntity<>(reservationService.editReservationById(editReservationParams), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RentalCarsSearchRentalOffersResult> searchOffers(
            @RequestBody SearchOffersRequiredParametersDTO requiredParametersDTO) {
        return new ResponseEntity<>(reservationService.searchOffers(requiredParametersDTO), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RentalCarsReservationMinimumDTO> createReservation(
            @RequestBody RentalCarsReservationParams reservationParams) {
        return new ResponseEntity<>(reservationService.createReservation(reservationParams), HttpStatus.OK);
    }
}
