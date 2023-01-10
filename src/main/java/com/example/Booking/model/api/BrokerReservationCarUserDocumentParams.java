package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokerReservationCarUserDocumentParams {
    private String number;
    private int type;
    private String expiryDate;

}
