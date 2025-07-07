package com.nmquan1503.backend_springboot.dtos.responses.ticket;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketPriceResponse {

    Integer id;
    Byte dayOfWeek;
    LocalTime timeRangeStart;
    LocalTime timeRangeEnd;
    Double price;

}
