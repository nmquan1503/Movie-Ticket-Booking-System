package com.nmquan1503.backend_springboot.mappers.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypeSimpleResponse;
import com.nmquan1503.backend_springboot.entities.theater.RoomType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RoomTypeMapper {

    RoomTypeSimpleResponse toRoomTypeSummaryResponse(RoomType roomType);

}
