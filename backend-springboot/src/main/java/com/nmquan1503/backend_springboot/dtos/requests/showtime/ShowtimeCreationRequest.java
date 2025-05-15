package com.nmquan1503.backend_springboot.dtos.requests.showtime;

import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeCreationRequest {

    @NotNull(message = "MOVIE_EMPTY")
    Long movieId;

    @NotNull(message = "ROOM_EMPTY")
    Integer roomId;

    @NotNull(message = "START_TIME_SHOWTIME_EMPTY")
    LocalDateTime startTime;

}
