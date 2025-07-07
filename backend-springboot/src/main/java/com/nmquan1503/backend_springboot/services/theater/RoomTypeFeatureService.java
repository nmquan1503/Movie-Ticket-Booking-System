package com.nmquan1503.backend_springboot.services.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypeFeatureResponse;
import com.nmquan1503.backend_springboot.entities.theater.RoomTypeFeature;
import com.nmquan1503.backend_springboot.mappers.theater.RoomTypeFeatureMapper;
import com.nmquan1503.backend_springboot.repositories.theater.RoomTypeFeatureRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomTypeFeatureService {

    RoomTypeFeatureRepository roomTypeFeatureRepository;

    RoomTypeFeatureMapper roomTypeFeatureMapper;

    List<RoomTypeFeatureResponse> getRoomTypeFeaturesByRoomId(Byte roomTypeId) {
        return roomTypeFeatureMapper.toListRoomTypeFeatureResponse(
                roomTypeFeatureRepository.findByRoomTypeId(roomTypeId)
        );
    }

}
