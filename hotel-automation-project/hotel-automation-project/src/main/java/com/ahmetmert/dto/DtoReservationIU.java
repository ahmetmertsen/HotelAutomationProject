package com.ahmetmert.dto;
import java.time.LocalDateTime;

import com.ahmetmert.entities.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoReservationIU {
    
	@NotNull
	private Long customerId;
    
	@NotNull
	private Long roomId;
    
	@NotNull
    @FutureOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime checkInDate;
    
	@NotNull
    @Future
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime checkOutDate;
    
	@NotNull
	private ReservationStatus status;
    
	@NotNull
	@Positive
	private Long totalPrice;
}
