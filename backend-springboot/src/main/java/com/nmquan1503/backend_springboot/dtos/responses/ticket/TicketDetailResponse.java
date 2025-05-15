package com.nmquan1503.backend_springboot.dtos.responses.ticket;

import com.nmquan1503.backend_springboot.dtos.responses.product.ProductTicketItemResponse;
import com.nmquan1503.backend_springboot.dtos.responses.showtime.ShowtimeDetailResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.SeatDetailResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketDetailResponse {

    Long id;
    ShowtimeDetailResponse showtime;
    List<SeatDetailResponse> seats;
    List<ProductTicketItemResponse> items;
    TicketStatusResponse status;

}
