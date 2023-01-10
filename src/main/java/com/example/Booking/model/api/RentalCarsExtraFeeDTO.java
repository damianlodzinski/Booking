package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarsExtraFeeDTO {
    private String feeTypeId;
    private String description;
    private boolean payLocal;
    private BrokerExtraPriceParams charge;
}
