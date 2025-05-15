package com.nmquan1503.backend_springboot.repositories.theater.custom;

import com.nmquan1503.backend_springboot.entities.theater.Seat;

import java.util.List;

public interface CustomSeatRepository {

    boolean availableByIds(List<Long> ids);

    boolean areAvailable(List<Long> seatIds, Long showtimeId);

    List<Seat> findByTicketId(Long ticketId);

    List<Long> findLockedSeatIdsByShowtimeId(Long showtimeId);

    List<Seat> findByShowtimeId(Long showtimeId);

}
