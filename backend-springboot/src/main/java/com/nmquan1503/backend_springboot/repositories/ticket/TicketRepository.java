package com.nmquan1503.backend_springboot.repositories.ticket;

import com.nmquan1503.backend_springboot.entities.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
