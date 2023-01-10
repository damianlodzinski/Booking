package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingCarUserDTOer {
    private String firstName;
    private String lastName;
    private String airline;
    private String flightNumber;
    private String email;
    private String dateOfBirth;
}
