package com.nmquan1503.backend_springboot.repositories.ticket.custom;

import java.time.LocalDateTime;

public interface CustomTicketPriceRepository {

    Double findPriceByRoomTypeIdAndSeatTypeIdAndStartTime(Byte roomTypeId, Byte seatTypeId, LocalDateTime startTime);

}
