package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrokerLocationDTO {
    private int id;
    private int type;
    private String code;
    private String address;
    private String city;
    private String postcode;
    private String email;
    private GeoCoordinatesDTO geoCoordinates;
}
