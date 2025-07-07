package com.nmquan1503.backend_springboot.repositories.theater.custom.impl;

import com.nmquan1503.backend_springboot.entities.reservation.QReservation;
import com.nmquan1503.backend_springboot.entities.reservation.QReservationSeat;
import com.nmquan1503.backend_springboot.entities.showtime.QShowtime;
import com.nmquan1503.backend_springboot.entities.theater.QRoom;
import com.nmquan1503.backend_springboot.entities.theater.QSeat;
import com.nmquan1503.backend_springboot.entities.theater.Seat;
import com.nmquan1503.backend_springboot.entities.ticket.QTicket;
import com.nmquan1503.backend_springboot.repositories.theater.custom.CustomSeatRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomSeatRepositoryImpl implements CustomSeatRepository {

    JPAQueryFactory queryFactory;

    @Override
    public boolean availableByIds(List<Long> ids) {
        QSeat seat = QSeat.seat;
        return queryFactory
                .select(seat.id)
                .from(seat)
                .where(seat.id.in(ids)
                        .and(seat.status.name.ne("AVAILABLE")))
                .fetch()
                .isEmpty();
    }

    @Override
    public boolean areAvailable(List<Long> seatIds, Long showtimeId) {
        QSeat seat = QSeat.seat;
        QTicket ticket = QTicket.ticket;

        Long count = queryFactory
                .select(seat.count())
                .from(seat)
                .join(seat.room, ticket.reservation.showtime.room)
                .where(ticket.reservation.showtime.id.eq(showtimeId)
                        .and(seat.id.in(seatIds)))
                .fetchOne();
        return count == null || count == 0;
    }

    @Override
    public List<Seat> findByTicketId(Long ticketId) {
        QSeat seat = QSeat.seat;
        QReservationSeat reservationSeat = QReservationSeat.reservationSeat;
        QTicket ticket = QTicket.ticket;

        return queryFactory
                .select(seat)
                .from(ticket)
                .join(ticket.reservation, reservationSeat.reservation)
                .join(reservationSeat.seat, seat)
                .where(ticket.id.eq(ticketId))
                .fetch();
    }

    @Override
    public List<Long> findLockedSeatIdsByShowtimeId(Long showtimeId) {
        QReservationSeat reservationSeat = QReservationSeat.reservationSeat;
        QReservation reservation = QReservation.reservation;
        return queryFactory
                .select(reservationSeat.seat.id)
                .from(reservationSeat)
                .join(reservationSeat.reservation, reservation)
                .where(reservation.showtime.id.eq(showtimeId)
                        .and(reservation.status.name.eq("PAID")
                                .or(reservation.status.name.eq("PENDING")
                                        .and(reservation.endTime.after(LocalDateTime.now())))))
                .fetch();
    }

    @Override
    public List<Seat> findByShowtimeId(Long showtimeId) {
        QShowtime showtime = QShowtime.showtime;
        QSeat seat = QSeat.seat;

        return queryFactory
                .select(seat)
                .from(seat)
                .join(showtime).on(seat.room.id.eq(showtime.room.id))
                .where(showtime.id.eq(showtimeId))
                .fetch();
    }

    @Override
    public List<Seat> findByReservationId(Long reservationId) {
        QReservationSeat reservationSeat = QReservationSeat.reservationSeat;
        QSeat seat = QSeat.seat;

        return queryFactory
                .select(seat)
                .from(seat)
                .join(reservationSeat).on(reservationSeat.seat.id.eq(seat.id))
                .where(reservationSeat.reservation.id.eq(reservationId))
                .fetch();
    }

}
