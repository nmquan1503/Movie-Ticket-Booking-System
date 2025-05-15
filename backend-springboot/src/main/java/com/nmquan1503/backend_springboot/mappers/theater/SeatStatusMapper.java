package com.nmquan1503.backend_springboot.mappers.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.SeatStatusResponse;
import com.nmquan1503.backend_springboot.entities.theater.SeatStatus;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SeatStatusMapper {

    SeatStatusResponse toSeatStatusSummaryResponse(SeatStatus seatStatus);

}
