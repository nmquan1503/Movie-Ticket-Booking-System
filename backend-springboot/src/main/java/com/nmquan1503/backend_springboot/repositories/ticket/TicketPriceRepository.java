package com.nmquan1503.backend_springboot.repositories.ticket;

import com.nmquan1503.backend_springboot.entities.ticket.TicketPrice;
import com.nmquan1503.backend_springboot.repositories.ticket.custom.CustomTicketPriceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TicketPriceRepository extends JpaRepository<TicketPrice, Integer>, CustomTicketPriceRepository {
}
