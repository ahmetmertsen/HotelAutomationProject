package com.ahmetmert.controller;

import java.util.List;

import com.ahmetmert.dto.DtoRoom;
import com.ahmetmert.dto.DtoRoomIU;

public interface IRestRoomController {
	
    public RestRootEntity<List<DtoRoom>> getAllRooms();


    public RestRootEntity<DtoRoom> getRoomById(Long id);


    public RestRootEntity<DtoRoom> addRoom(DtoRoomIU dtoRoomIU);


    public RestRootEntity<Void> deleteRoom(Long id);

}
