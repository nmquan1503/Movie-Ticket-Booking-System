package com.nmquan1503.backend_springboot.services.reservation;

import com.nmquan1503.backend_springboot.entities.reservation.ReservationStatus;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.repositories.reservation.ReservationStatusRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationStatusService {

    ReservationStatusRepository reservationStatusRepository;

    ReservationStatus fetchByName(String name) {
        return reservationStatusRepository.findByName(name)
                .orElseThrow(() -> new GeneralException(ResponseCode.RESERVATION_STATUS_NOT_FOUND));
    }

}
