package com.nmquan1503.backend_springboot.repositories.theater;

import com.nmquan1503.backend_springboot.entities.theater.Seat;
import com.nmquan1503.backend_springboot.repositories.theater.custom.CustomSeatRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long>, CustomSeatRepository {

    List<Seat> findByRoomId(Integer id);

}
