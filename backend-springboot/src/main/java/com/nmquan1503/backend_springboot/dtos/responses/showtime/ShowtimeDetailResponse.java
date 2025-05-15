package com.nmquan1503.backend_springboot.dtos.responses.showtime;

import com.nmquan1503.backend_springboot.dtos.responses.movie.MoviePreviewResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomDetailResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeDetailResponse {

    Long id;
    MoviePreviewResponse movie;
    RoomDetailResponse room;
    LocalDate startTime;

}
