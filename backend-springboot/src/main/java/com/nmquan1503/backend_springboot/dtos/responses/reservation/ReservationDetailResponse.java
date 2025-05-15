package com.nmquan1503.backend_springboot.dtos.responses.reservation;

import com.nmquan1503.backend_springboot.dtos.responses.product.ProductReservationItemResponse;
import com.nmquan1503.backend_springboot.dtos.responses.showtime.ShowtimeDetailResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.SeatDetailResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationDetailResponse {

    Long id;
    ShowtimeDetailResponse showtime;
    Double ticketPrice;
    LocalDateTime startTime;
    LocalDateTime endTime;
    List<SeatDetailResponse> seats;
    List<ProductReservationItemResponse> items;
    ReservationStatusResponse status;

}
