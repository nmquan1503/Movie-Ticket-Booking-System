package com.nmquan1503.backend_springboot.controllers.ticket;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.ticket.TicketDetailResponse;
import com.nmquan1503.backend_springboot.services.ticket.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketController {

    TicketService ticketService;

    @GetMapping("/details/{ticketId}")
    public ResponseEntity<ApiResponse<TicketDetailResponse>> getTicketDetail(
            @PathVariable Long ticketId
    ) {
        ApiResponse<TicketDetailResponse> response = ApiResponse.success(
                ticketService.getTicketDetail(ticketId)
        );
        return ResponseEntity.ok().body(response);
    }

}
