package com.nmquan1503.backend_springboot.dtos.responses.ticket;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketStatusResponse {

    Byte id;
    String name;

}
