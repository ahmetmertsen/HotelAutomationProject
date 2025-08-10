package com.ahmetmert.dto;

import com.ahmetmert.entities.RoomType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoRoom {

	 private Long roomId;
	
	 private Long roomNo;
	 
	 private RoomType roomType;
	 
	 private Long price;
	 
	 private int capacity;
	 
	 private boolean occupied;
	 
	 private boolean clean;
	 
}
