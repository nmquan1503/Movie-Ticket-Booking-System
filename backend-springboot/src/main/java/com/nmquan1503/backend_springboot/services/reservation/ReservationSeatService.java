package com.nmquan1503.backend_springboot.services.reservation;

import com.nmquan1503.backend_springboot.entities.reservation.Reservation;
import com.nmquan1503.backend_springboot.entities.reservation.ReservationSeat;
import com.nmquan1503.backend_springboot.entities.theater.Seat;
import com.nmquan1503.backend_springboot.repositories.reservation.ReservationSeatRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationSeatService {

    ReservationSeatRepository reservationSeatRepository;

    public void save(Long reservationId, List<Long> seatIds) {
        Reservation reservation = Reservation.builder().id(reservationId).build();
        List<ReservationSeat> reservationSeats = seatIds.stream().map(
                seatId -> ReservationSeat.builder()
                        .reservation(reservation)
                        .seat(Seat.builder().id(seatId).build())
                        .build()
        ).toList();
        reservationSeatRepository.saveAll(reservationSeats);
    }

    public void save(Reservation reservation, List<Seat> seats) {
        List<ReservationSeat> reservationSeats = seats.stream().map(
                seat -> ReservationSeat.builder()
                        .reservation(reservation)
                        .seat(seat)
                        .build()
        ).toList();
        reservationSeatRepository.saveAll(reservationSeats);
    }

    public List<ReservationSeat> fetchByReservationId(Long reservationId) {
        return reservationSeatRepository.findByReservationId(reservationId);
    }

}
