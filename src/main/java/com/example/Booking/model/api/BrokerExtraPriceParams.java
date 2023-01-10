package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrokerExtraPriceParams {
    private boolean includedInRate;
    private double amount;
    private String currency;
    private double taxRate;
    private boolean taxInclusive;
}
