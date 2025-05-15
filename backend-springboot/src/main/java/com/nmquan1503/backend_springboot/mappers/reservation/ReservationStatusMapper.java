package com.nmquan1503.backend_springboot.mappers.reservation;

import com.nmquan1503.backend_springboot.dtos.responses.reservation.ReservationStatusResponse;
import com.nmquan1503.backend_springboot.entities.reservation.ReservationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ReservationStatusMapper {

    ReservationStatusResponse toReservationStatusSummaryResponse(ReservationStatus status);

}
