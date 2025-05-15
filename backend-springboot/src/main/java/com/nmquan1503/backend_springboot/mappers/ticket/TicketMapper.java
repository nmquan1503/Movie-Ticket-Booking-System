package com.nmquan1503.backend_springboot.mappers.ticket;

import com.nmquan1503.backend_springboot.dtos.responses.ticket.TicketDetailResponse;
import com.nmquan1503.backend_springboot.entities.ticket.Ticket;
import com.nmquan1503.backend_springboot.mappers.showtime.ShowtimeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                ShowtimeMapper.class
        }
)
public interface TicketMapper {

    TicketDetailResponse toTicketDetailResponse(Ticket ticket);

}
