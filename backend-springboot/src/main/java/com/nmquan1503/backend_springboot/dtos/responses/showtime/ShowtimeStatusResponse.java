package com.nmquan1503.backend_springboot.dtos.responses.showtime;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeStatusResponse {

    Byte id;
    String name;

}
