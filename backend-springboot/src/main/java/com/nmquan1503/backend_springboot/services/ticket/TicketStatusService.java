package com.nmquan1503.backend_springboot.services.ticket;

import com.nmquan1503.backend_springboot.entities.ticket.TicketStatus;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.repositories.ticket.TicketStatusRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketStatusService {

    TicketStatusRepository ticketStatusRepository;

    TicketStatus fetchByName(String name) {
        return ticketStatusRepository.findByName(name)
                .orElseThrow(() -> new GeneralException(ResponseCode.TICKET_STATUS_NOT_FOUND));
    }

}
