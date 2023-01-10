package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrokerRentAdditionDTO {
    private String type;
    private String description;
    private String descriptionPL;
    private String descriptionEN;
}
