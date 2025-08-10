package com.ahmetmert.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmetmert.dto.DtoRoom;
import com.ahmetmert.dto.DtoRoomIU;
import com.ahmetmert.entities.Room;
import com.ahmetmert.repository.RoomRepository;
import com.ahmetmert.services.IRoomService;

@Service
public class RoomServiceImpl implements IRoomService {
	
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public List<DtoRoom> getAllRooms() {
		List<DtoRoom> dtoList = new ArrayList<>();
		   
		List<Room> dbList = roomRepository.findAll();
		
		for (Room room : dbList) {
			DtoRoom dtoRoom = new DtoRoom(); 
			BeanUtils.copyProperties(room, dtoRoom);
			dtoList.add(dtoRoom);
		}
		return dtoList;
	}

	@Override
	public DtoRoom getRoomById(Long id) {
    	DtoRoom dtoRoom = new DtoRoom();
        Optional<Room> optional = roomRepository.findById(id);
        if(optional.isEmpty()) {
        	return null;
        }
        Room room = optional.get();
        BeanUtils.copyProperties(room, dtoRoom);
        return dtoRoom;
	}

	@Override
	public DtoRoom saveRoom(DtoRoomIU dtoRoomIU) {
    	DtoRoom responseRoom = new DtoRoom();
        Room room = new Room();
        room.setRoomNo(dtoRoomIU.getRoomNo());
        room.setRoomType(dtoRoomIU.getRoomType());
        room.setCapacity(dtoRoomIU.getCapacity());
        room.setPrice(dtoRoomIU.getPrice());
        
        Room dbRoom = roomRepository.save(room);
        BeanUtils.copyProperties(dbRoom, responseRoom);
        
        return responseRoom;
	}

	@Override
	public void deleteRoom(Long id) {
		Optional<Room> optional = roomRepository.findById(id);
		if(optional.isPresent()) {
			roomRepository.delete(optional.get());
		}
		
	}

}
