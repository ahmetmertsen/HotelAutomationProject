package com.ahmetmert.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmetmert.controller.IRestReservationController;
import com.ahmetmert.dto.DtoReservation;
import com.ahmetmert.dto.DtoReservationIU;
import com.ahmetmert.service.IReservationService;

@RestController
@RequestMapping("/reservation")
public class RestReservationControllerImpl implements IRestReservationController {

    @Autowired
    private IReservationService reservationService;

    @GetMapping(path = "/list")
    @Override
    public List<DtoReservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping(path = "/list/{id}")
    @Override
    public DtoReservation getReservationById(@PathVariable(name = "id") Long id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping(path = "/add")
    @Override
    public DtoReservation addReservation(@RequestBody DtoReservationIU dtoReservationIU) {
        return reservationService.saveReservation(dtoReservationIU);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public void deleteReservation(@PathVariable(name = "id") Long id) {
        reservationService.deleteReservation(id);
    }
}
