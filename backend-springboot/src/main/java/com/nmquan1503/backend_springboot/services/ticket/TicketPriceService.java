package com.nmquan1503.backend_springboot.services.ticket;

import com.nmquan1503.backend_springboot.entities.reservation.Reservation;
import com.nmquan1503.backend_springboot.entities.reservation.ReservationSeat;
import com.nmquan1503.backend_springboot.repositories.ticket.TicketPriceRepository;
import com.nmquan1503.backend_springboot.services.reservation.ReservationSeatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketPriceService {

    TicketPriceRepository ticketPriceRepository;

    ReservationSeatService reservationSeatService;

    public double getTotalTicketPrice(Reservation reservation) {
        List<ReservationSeat> reservationSeats = reservationSeatService.fetchByReservationId(reservation.getId());
        double total = 0.0;
        for (ReservationSeat reservationSeat : reservationSeats) {
            total += ticketPriceRepository.findPriceByRoomTypeIdAndSeatTypeIdAndStartTime(
                    reservationSeat.getSeat().getRoom().getType().getId(),
                    reservationSeat.getSeat().getType().getId(),
                    reservation.getStartTime()
            );
        }
        return total;
    }

}
