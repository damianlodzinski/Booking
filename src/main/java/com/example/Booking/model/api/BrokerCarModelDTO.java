package com.example.Booking.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrokerCarModelDTO {
    private int id;
    private String name;
    private String vehicleClass;
    private String vehicleClassAlias;
    private String exampleCarPhoto;
    private int type;
    private ArrayList<Integer> typeArray;
}
