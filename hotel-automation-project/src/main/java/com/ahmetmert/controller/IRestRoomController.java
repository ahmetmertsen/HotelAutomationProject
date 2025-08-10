package com.ahmetmert.controller;

import java.util.List;

import com.ahmetmert.dto.DtoRoom;
import com.ahmetmert.dto.DtoRoomIU;

public interface IRestRoomController {
	
    public List<DtoRoom> getAllRooms();


    public DtoRoom getRoomById(Long id);


    public DtoRoom addRoom(DtoRoomIU dtoRoomIU);


    public void deleteRoom(Long id);

}
