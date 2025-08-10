package com.ahmetmert.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmetmert.controller.IRestRoomController;
import com.ahmetmert.dto.DtoRoom;
import com.ahmetmert.dto.DtoRoomIU;
import com.ahmetmert.services.IRoomService;

@RestController
@RequestMapping("/room")
public class RestRoomControllerImpl implements IRestRoomController {

	@Autowired
	private IRoomService roomService;
	
	@GetMapping(path = "/list")
	@Override
	public List<DtoRoom> getAllRooms() {
		return roomService.getAllRooms();
	}

	@GetMapping(path = "/list/{id}")
	@Override
	public DtoRoom getRoomById(@PathVariable(name = "id") Long id) {
		return roomService.getRoomById(id);
	}

	@PostMapping(path = "/add")
	@Override
	public DtoRoom addRoom(@RequestBody DtoRoomIU dtoRoomIU) {
		 return roomService.saveRoom(dtoRoomIU);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteRoom(@PathVariable(name = "id") Long id) {
		roomService.deleteRoom(id);
		
	}

}
