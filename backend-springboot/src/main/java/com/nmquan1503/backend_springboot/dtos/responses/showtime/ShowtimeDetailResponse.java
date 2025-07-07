package com.nmquan1503.backend_springboot.dtos.responses.showtime;

import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieBannerResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MoviePreviewResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomDetailResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeDetailResponse {

    Long id;
    MovieBannerResponse movie;
    RoomDetailResponse room;
    LocalDateTime startTime;

}
