package com.nmquan1503.backend_springboot.mappers.reservation;

import com.nmquan1503.backend_springboot.dtos.responses.reservation.ReservationDetailResponse;
import com.nmquan1503.backend_springboot.entities.reservation.Reservation;
import com.nmquan1503.backend_springboot.mappers.showtime.ShowtimeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                ShowtimeMapper.class,
                ReservationStatusMapper.class
        }
)
public interface ReservationMapper {

    ReservationDetailResponse toReservationDetailResponse(Reservation reservation);

}
