package com.ahmetmert.controller;

import java.util.List;

import com.ahmetmert.dto.DtoReservation;
import com.ahmetmert.dto.DtoReservationIU;

public interface IRestReservationController {

	public List<DtoReservation> getAllReservations();
	
	public DtoReservation getReservationById(Long id);
	
	public DtoReservation addReservation(DtoReservationIU dtoReservationIU);
	
	public void deleteReservation(Long id);
	
}
