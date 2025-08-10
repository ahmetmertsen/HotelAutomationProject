package com.ahmetmert.services;

import java.util.List;

import com.ahmetmert.dto.DtoRoom;
import com.ahmetmert.dto.DtoRoomIU;

public interface IRoomService {
	
	public List<DtoRoom> getAllRooms();
	
	public DtoRoom getRoomById(Long id);
	
	public DtoRoom saveRoom(DtoRoomIU dtoRoomIU);
	
	public void deleteRoom(Long id);

}
