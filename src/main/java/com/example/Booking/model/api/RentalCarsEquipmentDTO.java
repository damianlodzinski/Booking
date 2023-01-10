package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCarsEquipmentDTO {
    private int extraTypeId;
    private int quantity;
    private String description;
    private boolean prepaid;
    private RentalCarsPriceParams price;
}
