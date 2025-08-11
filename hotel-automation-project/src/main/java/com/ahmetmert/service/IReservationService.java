package com.ahmetmert.service;

import java.util.List;

import com.ahmetmert.dto.DtoReservation;
import com.ahmetmert.dto.DtoReservationIU;

public interface IReservationService {
	
	public List<DtoReservation> getAllReservations();
	
	public DtoReservation getReservationById(Long id);
	
	public DtoReservation saveReservation(DtoReservationIU dtoReservationIU);
	
	public void deleteReservation(Long id);

}
