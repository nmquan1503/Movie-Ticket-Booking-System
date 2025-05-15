package com.nmquan1503.backend_springboot.dtos.requests.showtime;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeUpdateRequest {

    Byte showtimeStatusId;

}
