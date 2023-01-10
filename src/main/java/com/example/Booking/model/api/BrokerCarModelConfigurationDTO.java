package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrokerCarModelConfigurationDTO {
    private int id;
    private String fullModelName;
    private String versionName;
    private int fuelType;
    private int maxPassengersAmount;
    private String carClassAcriss;
}
