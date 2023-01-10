package com.example.Booking.controller;

import com.example.Booking.model.AdditionModel;
import com.example.Booking.model.api.BrokerRentAdditionDTO;
import com.example.Booking.service.ExtraService;
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
@RequestMapping("/extra")
public class ExtrasController {
    private final ExtraService extraService;

    @GetMapping("/api")
    public ResponseEntity<BrokerRentAdditionDTO[]> getAllExtrasFromApi() {
        return new ResponseEntity<>(extraService.getExtrasFromApi(), HttpStatus.OK);
    }

    @GetMapping("/db")
    public ResponseEntity<Page<AdditionModel>> getAllExtrasFromDb(Pageable pageable) {
        return new ResponseEntity<>(extraService.getExtrasFromDb(pageable), HttpStatus.OK);
    }

}
