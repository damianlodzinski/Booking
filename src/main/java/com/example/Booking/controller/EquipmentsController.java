package com.example.Booking.controller;

import com.example.Booking.model.EquipmentModel;
import com.example.Booking.service.EquipmentsService;
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
@RequestMapping("/equipments")
public class EquipmentsController {
    private final EquipmentsService equipmentsService;

    @GetMapping("/db")
    public ResponseEntity<Page<EquipmentModel>> getAllEquipmentsFromDb(Pageable pageable) {
        return new ResponseEntity<>(equipmentsService.getEquipmentsFromDb(pageable), HttpStatus.OK);
    }

}
