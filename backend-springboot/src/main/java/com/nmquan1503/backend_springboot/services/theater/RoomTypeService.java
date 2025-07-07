package com.nmquan1503.backend_springboot.services.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypeDetailResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypeIconResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypePreviewResponse;
import com.nmquan1503.backend_springboot.entities.theater.RoomType;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.theater.RoomTypeMapper;
import com.nmquan1503.backend_springboot.repositories.theater.RoomTypeRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomTypeService {

    RoomTypeRepository roomTypeRepository;

    RoomTypeMapper roomTypeMapper;

    RoomTypeFeatureService roomTypeFeatureService;
    BranchService branchService;

    public List<RoomTypePreviewResponse> getAllRoomTypePreviews() {
        return roomTypeMapper.toListRoomTypePreviewResponse(
                roomTypeRepository.findAll()
        );
    }

    public List<RoomTypeIconResponse> getAllRoomTypeIcons() {
        return roomTypeMapper.toListRoomTypeIconResponse(
                roomTypeRepository.findAll()
        );
    }

    public RoomTypeDetailResponse getRoomTypeDetailById(Byte id) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ResponseCode.ROOM_TYPE_NOT_FOUND));
        RoomTypeDetailResponse response = roomTypeMapper.toRoomTypeDetailResponse(roomType);
        response.setFeatures(roomTypeFeatureService.getRoomTypeFeaturesByRoomId(id));
        response.setBranches(branchService.getBranchSimplesByRoomTypeId(id));
        return response;
    }

}
