package com.nmquan1503.backend_springboot.mappers.showtime;

import com.nmquan1503.backend_springboot.dtos.responses.showtime.ShowtimeDetailResponse;
import com.nmquan1503.backend_springboot.dtos.responses.showtime.ShowtimeOptionResponse;
import com.nmquan1503.backend_springboot.entities.showtime.Showtime;
import com.nmquan1503.backend_springboot.mappers.movie.MovieMapper;
import com.nmquan1503.backend_springboot.mappers.theater.RoomMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                RoomMapper.class,
                MovieMapper.class,

        }
)
public interface ShowtimeMapper {

    ShowtimeOptionResponse toShowtimeSummaryResponse(Showtime showtime);

    ShowtimeDetailResponse toShowtimeDetailResponse(Showtime showtime);

}
