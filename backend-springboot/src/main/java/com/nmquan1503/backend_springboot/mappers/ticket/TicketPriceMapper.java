package com.nmquan1503.backend_springboot.mappers.ticket;

import com.nmquan1503.backend_springboot.dtos.responses.ticket.TicketPriceResponse;
import com.nmquan1503.backend_springboot.entities.ticket.TicketPrice;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TicketPriceMapper {

    TicketPriceResponse toTicketPriceResponse(TicketPrice ticketPrice);

    List<TicketPriceResponse> toListTicketPriceResponse(List<TicketPrice> ticketPrices);

}
