package com.nmquan1503.backend_springboot.dtos.responses.reservation;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationStatusResponse {

    Byte id;
    String name;

}
