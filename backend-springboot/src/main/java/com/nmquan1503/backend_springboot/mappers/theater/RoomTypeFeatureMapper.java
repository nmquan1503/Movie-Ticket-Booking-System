package com.nmquan1503.backend_springboot.mappers.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypeFeatureResponse;
import com.nmquan1503.backend_springboot.entities.theater.RoomTypeFeature;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RoomTypeFeatureMapper {

    RoomTypeFeatureResponse toRoomTypeFeatureResponse(RoomTypeFeature roomTypeFeature);

    List<RoomTypeFeatureResponse> toListRoomTypeFeatureResponse(List<RoomTypeFeature> roomTypeFeatures);

}
