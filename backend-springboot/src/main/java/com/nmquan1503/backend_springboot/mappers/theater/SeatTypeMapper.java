package com.nmquan1503.backend_springboot.mappers.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.SeatTypeResponse;
import com.nmquan1503.backend_springboot.entities.theater.SeatType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SeatTypeMapper {

    SeatTypeResponse toSeatTypeSummaryResponse(SeatType seatType);

}
