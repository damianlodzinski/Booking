package com.example.Booking.repository;

import com.example.Booking.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
}
