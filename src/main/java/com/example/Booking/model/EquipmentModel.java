package com.example.Booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentModel {
    private String type;
    private String descriptionPL;
    private String descriptionEN;
}
