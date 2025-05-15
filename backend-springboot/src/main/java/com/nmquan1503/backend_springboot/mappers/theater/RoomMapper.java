package com.nmquan1503.backend_springboot.mappers.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomDetailResponse;
import com.nmquan1503.backend_springboot.entities.theater.Room;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {RoomTypeMapper.class, BranchMapper.class}
)
public interface RoomMapper {

    RoomDetailResponse toRoomDetailResponse(Room room);

}
