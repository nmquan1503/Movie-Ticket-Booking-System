package com.nmquan1503.backend_springboot.repositories.reservation;

import com.nmquan1503.backend_springboot.entities.reservation.ReservationProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationProductRepository extends JpaRepository<ReservationProduct, Long> {

    List<ReservationProduct> findByReservationId(Long reservationId);

}
