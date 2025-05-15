package com.nmquan1503.backend_springboot.repositories.ticket;

import com.nmquan1503.backend_springboot.entities.ticket.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketStatusRepository extends JpaRepository<TicketStatus, Byte> {

    Optional<TicketStatus> findByName(String name);

}
