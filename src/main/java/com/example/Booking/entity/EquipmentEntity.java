package com.example.Booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "equipment")
public class EquipmentEntity {
    @Id
    private String type;
    private String descriptionPL;
    private String descriptionEN;
}
