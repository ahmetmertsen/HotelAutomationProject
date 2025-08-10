package com.ahmetmert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahmetmert.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
