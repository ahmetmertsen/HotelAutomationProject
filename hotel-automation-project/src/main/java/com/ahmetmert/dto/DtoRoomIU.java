package com.ahmetmert.dto;

import com.ahmetmert.entity.RoomType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoRoomIU {
	
	@NotNull
	private Long roomNo;
	
	private RoomType roomType;
	
	@Min(1)
	private int capacity;
	
	@NotNull
    @Positive
	private Long price;
	
	
}

