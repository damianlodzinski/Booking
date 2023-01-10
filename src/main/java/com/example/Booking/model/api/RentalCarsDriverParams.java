package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarsDriverParams {
    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String stateOrProvince;
    private String city;
    private String country;
    private String airline;
    private String flightNumber;
    private String email;
    private String phone;
    private String mobilePhone;
    private String pesel;
    private String countryCode;
    private String houseNumber;
    private String flatNumber;
    private String dateOfBirth;
    private BrokerReservationCarUserDocumentParams passport;
    private BrokerReservationCarUserDocumentParams drivingLicense;
}
