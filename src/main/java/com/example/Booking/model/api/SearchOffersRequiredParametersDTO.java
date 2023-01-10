package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchOffersRequiredParametersDTO {
    private String pickUpDate;
    private String dropOffDate;
    private String supplierPickUpLocCode;
    private String supplierDropOffLocCode;
}
