package com.nmquan1503.backend_springboot.controllers.ticket;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.ticket.TicketPriceResponse;
import com.nmquan1503.backend_springboot.services.ticket.TicketPriceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/ticket-price")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketPriceController {

    TicketPriceService ticketPriceService;

    @GetMapping
    ResponseEntity<ApiResponse<List<TicketPriceResponse>>> getAllBaseTicketPrice() {
        ApiResponse<List<TicketPriceResponse>> response = ApiResponse.success(
                ticketPriceService.getAllBaseTicketPrice()
        );
        return ResponseEntity.ok().body(response);
    }

}
