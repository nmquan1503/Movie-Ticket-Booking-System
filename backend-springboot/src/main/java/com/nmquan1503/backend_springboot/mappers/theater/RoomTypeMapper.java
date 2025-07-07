package com.nmquan1503.backend_springboot.mappers.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypeDetailResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypeIconResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypePreviewResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypeSimpleResponse;
import com.nmquan1503.backend_springboot.entities.theater.RoomType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RoomTypeMapper {

    RoomTypeSimpleResponse toRoomTypeSummaryResponse(RoomType roomType);

    RoomTypePreviewResponse toRoomTypePreviewResponse(RoomType roomType);

    List<RoomTypePreviewResponse> toListRoomTypePreviewResponse(List<RoomType> roomTypes);

    RoomTypeIconResponse toRoomTypeIconResponse(RoomType roomType);

    List<RoomTypeIconResponse> toListRoomTypeIconResponse(List<RoomType> roomTypes);

    RoomTypeDetailResponse toRoomTypeDetailResponse(RoomType roomType);
}
