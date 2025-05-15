package com.nmquan1503.backend_springboot.services.theater;

import com.nmquan1503.backend_springboot.repositories.theater.RoomRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomService {

    RoomRepository roomRepository;

    public boolean existsByRoomId(Integer roomId) {
        return roomRepository.existsById(roomId);
    }

}
