package com.nmquan1503.backend_springboot.repositories.ticket.custom;

import java.time.LocalDateTime;

public interface CustomTicketPriceRepository {

    double findCurrentTicketPrice();

}
