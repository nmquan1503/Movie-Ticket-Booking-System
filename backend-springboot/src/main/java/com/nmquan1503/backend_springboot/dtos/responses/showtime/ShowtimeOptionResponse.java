package com.nmquan1503.backend_springboot.dtos.responses.showtime;

import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomDetailResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeOptionResponse {

    Long id;
    LocalDateTime startTime;
    RoomDetailResponse room;
    ShowtimeStatusResponse status;

}
