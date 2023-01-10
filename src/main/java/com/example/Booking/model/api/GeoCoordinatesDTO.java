package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoCoordinatesDTO {
    private double latitude;
    private double longitude;
}
