package com.example.Booking.controller;

import com.example.Booking.model.LocationModel;
import com.example.Booking.model.api.BrokerLocationDTO;
import com.example.Booking.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/api")
    public ResponseEntity<BrokerLocationDTO[]> getAllLocationsFromApi() {
        return new ResponseEntity<>(locationService.getLocationsFromApi(), HttpStatus.OK);
    }

    @GetMapping("/db")
    public ResponseEntity<Page<LocationModel>> getAllLocationsFromDb(Pageable pageable) {
        return new ResponseEntity<>(locationService.getLocationsFromDb(pageable), HttpStatus.OK);
    }
}
