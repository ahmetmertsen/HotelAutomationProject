package com.ahmetmert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmetmert.dto.DtoRoom;
import com.ahmetmert.dto.DtoRoomIU;
import com.ahmetmert.entity.Room;
import com.ahmetmert.exception.BaseException;
import com.ahmetmert.exception.ErrorMessage;
import com.ahmetmert.exception.MessageType;
import com.ahmetmert.repository.RoomRepository;
import com.ahmetmert.service.IRoomService;

@Service
public class RoomServiceImpl implements IRoomService {
	
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public List<DtoRoom> getAllRooms() {
		List<DtoRoom> dtoList = new ArrayList<>();
		   
		List<Room> dbList = roomRepository.findAll();
		if (dbList.isEmpty()) {
		    throw new BaseException(
		    		new ErrorMessage(MessageType.NO_RECORD_EXIST, null));
		}
		
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
        	throw new BaseException(
        			new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
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
        
        try {
        	Room dbRoom = roomRepository.save(room);
            BeanUtils.copyProperties(dbRoom, responseRoom);
            
            return responseRoom;
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.GENEREL_EXCEPTION, e.getMessage()));
		}  
	}

	@Override
	public void deleteRoom(Long id) {
		Optional<Room> optional = roomRepository.findById(id);
		if(optional.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
		}
		roomRepository.delete(optional.get());
	}

}
