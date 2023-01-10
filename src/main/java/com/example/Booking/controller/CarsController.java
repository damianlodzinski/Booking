package com.example.Booking.controller;

import com.example.Booking.model.CarModel;
import com.example.Booking.model.api.BrokerCarModelDTO;
import com.example.Booking.service.CarsService;
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
@RequestMapping("/car")
public class CarsController {
    private final CarsService carsService;

    @GetMapping("/api")
    public ResponseEntity<BrokerCarModelDTO[]> getAllCarsFromApi() {
        return new ResponseEntity<>(carsService.getCarsFromApi(), HttpStatus.OK);
    }

    @GetMapping("/db")
    public ResponseEntity<Page<CarModel>> getAllCarsFromDb(Pageable pageable) {
        return new ResponseEntity<>(carsService.getCarsFromDb(pageable), HttpStatus.OK);
    }
}
