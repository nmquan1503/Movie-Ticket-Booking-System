package com.nmquan1503.backend_springboot.mappers.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.SeatDetailResponse;
import com.nmquan1503.backend_springboot.entities.theater.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                SeatStatusMapper.class,
                SeatTypeMapper.class
        }
)
public interface SeatMapper {

    SeatDetailResponse toSeatDetailResponse(Seat seat);

    List<SeatDetailResponse> toListSeatDetailResponse(List<Seat> seats);

}
