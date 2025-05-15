package com.nmquan1503.backend_springboot.repositories.reservation;

import com.nmquan1503.backend_springboot.entities.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
