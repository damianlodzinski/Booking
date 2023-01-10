package com.example.Booking.repository;

import com.example.Booking.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {
}
