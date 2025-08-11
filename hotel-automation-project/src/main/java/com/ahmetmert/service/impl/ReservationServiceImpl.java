package com.ahmetmert.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmetmert.dto.DtoCustomer;
import com.ahmetmert.dto.DtoReservation;
import com.ahmetmert.dto.DtoReservationIU;
import com.ahmetmert.dto.DtoRoom;
import com.ahmetmert.entity.Customer;
import com.ahmetmert.entity.Reservation;
import com.ahmetmert.entity.Room;
import com.ahmetmert.repository.CustomerRepository;
import com.ahmetmert.repository.ReservationRepository;
import com.ahmetmert.repository.RoomRepository;
import com.ahmetmert.service.IReservationService;


@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<DtoReservation> getAllReservations() {
        List<DtoReservation> dtoList = new ArrayList<>();
        List<Reservation> dbList = reservationRepository.findAll();

        for (Reservation reservation : dbList) {
            DtoReservation dto = new DtoReservation();

            DtoCustomer dtoCustomer = new DtoCustomer();
            BeanUtils.copyProperties(reservation.getCustomer(), dtoCustomer);

            DtoRoom dtoRoom = new DtoRoom();
            BeanUtils.copyProperties(reservation.getRoom(), dtoRoom);

            BeanUtils.copyProperties(reservation, dto);
            dto.setCustomer(dtoCustomer);
            dto.setRoom(dtoRoom);

            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public DtoReservation getReservationById(Long id) {
        Optional<Reservation> optional = reservationRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        Reservation reservation = optional.get();

        DtoReservation dto = new DtoReservation();
        DtoCustomer dtoCustomer = new DtoCustomer();
        BeanUtils.copyProperties(reservation.getCustomer(), dtoCustomer);

        DtoRoom dtoRoom = new DtoRoom();
        BeanUtils.copyProperties(reservation.getRoom(), dtoRoom);

        BeanUtils.copyProperties(reservation, dto);
        dto.setCustomer(dtoCustomer);
        dto.setRoom(dtoRoom);

        return dto;
    }

    @Override
    public DtoReservation saveReservation(DtoReservationIU dtoReservationIU) {

        Customer customer = customerRepository.findById(dtoReservationIU.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Müşteri bulunamadı"));


        Room room = roomRepository.findById(dtoReservationIU.getRoomId())
                .orElseThrow(() -> new RuntimeException("Oda bulunamadı"));

        if (room.isOccupied()) {
            throw new RuntimeException("Oda zaten dolu!");
        }

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setRoom(room);
        reservation.setCheckInDate(dtoReservationIU.getCheckInDate());
        reservation.setCheckOutDate(dtoReservationIU.getCheckOutDate());
        reservation.setStatus(dtoReservationIU.getStatus());
        reservation.setTotalPrice(dtoReservationIU.getTotalPrice());
        reservation.setReservationDate(LocalDateTime.now());

        Reservation saved = reservationRepository.save(reservation);


        room.setOccupied(true);
        roomRepository.save(room);


        DtoReservation dto = new DtoReservation();
        DtoCustomer dtoCustomer = new DtoCustomer();
        BeanUtils.copyProperties(saved.getCustomer(), dtoCustomer);

        DtoRoom dtoRoom = new DtoRoom();
        BeanUtils.copyProperties(saved.getRoom(), dtoRoom);

        BeanUtils.copyProperties(saved, dto);
        dto.setCustomer(dtoCustomer);
        dto.setRoom(dtoRoom);

        return dto;
    }

    @Override
    public void deleteReservation(Long id) {
        Optional<Reservation> optional = reservationRepository.findById(id);
        if (optional.isPresent()) {
            Reservation reservation = optional.get();

            Room room = reservation.getRoom();
            room.setOccupied(false);
            roomRepository.save(room);

            reservationRepository.delete(reservation);
        }
    }
}

