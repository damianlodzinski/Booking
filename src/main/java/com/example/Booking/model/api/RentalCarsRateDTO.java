package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarsRateDTO {
    private String accountNumber;
    private String rateCode;
    private String discountNumber;
    private String rateReference;
}
