package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarsOfferDTO {
    private ArrayList<RentalCarsEquipmentDTO> extras;
    private ArrayList<Object> fees;
    private String vehicleClass;
    private String name;
    private String rateReference;
    private RentalCarsPriceParams price;
    private String status;
}
