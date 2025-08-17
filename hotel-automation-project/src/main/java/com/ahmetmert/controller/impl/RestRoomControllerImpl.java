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
import com.ahmetmert.controller.RestBaseController;
import com.ahmetmert.controller.RestRootEntity;
import com.ahmetmert.dto.DtoRoom;
import com.ahmetmert.dto.DtoRoomIU;
import com.ahmetmert.service.IRoomService;

@RestController
@RequestMapping("/room")
public class RestRoomControllerImpl extends RestBaseController implements IRestRoomController {

	@Autowired
	private IRoomService roomService;
	
	@GetMapping(path = "/list")
	@Override
	public RestRootEntity<List<DtoRoom>> getAllRooms() {
		return ok(roomService.getAllRooms());  
	}

	@GetMapping(path = "/list/{id}")
	@Override
	public RestRootEntity<DtoRoom> getRoomById(@PathVariable(name = "id") Long id) {
		return ok(roomService.getRoomById(id)); 
	}

	@PostMapping(path = "/add")
	@Override
	public RestRootEntity<DtoRoom> addRoom(@RequestBody DtoRoomIU dtoRoomIU) {
		 return ok(roomService.saveRoom(dtoRoomIU));
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public RestRootEntity<Void> deleteRoom(@PathVariable(name = "id") Long id) {
		roomService.deleteRoom(id);
		return ok(null);
		
	}

}
