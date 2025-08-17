package com.ahmetmert.controller;

import java.util.List;

import com.ahmetmert.dto.DtoReservation;
import com.ahmetmert.dto.DtoReservationIU;

public interface IRestReservationController {

	public RestRootEntity<List<DtoReservation>> getAllReservations();
	
	public RestRootEntity<DtoReservation> getReservationById(Long id);
	
	public RestRootEntity<DtoReservation> addReservation(DtoReservationIU dtoReservationIU);
	
	public RestRootEntity<Void> deleteReservation(Long id);
	
}
