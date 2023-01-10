package com.example.Booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationModel {
    private int id;
    private String code;
    private int type;
    private String address;
    private String city;
    private String postcode;
}
