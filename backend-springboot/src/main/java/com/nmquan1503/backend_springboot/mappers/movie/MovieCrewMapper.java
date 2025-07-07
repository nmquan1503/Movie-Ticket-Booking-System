package com.nmquan1503.backend_springboot.mappers.movie;

import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieCrewCreationRequest;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieCrewResponse;
import com.nmquan1503.backend_springboot.entities.movie.MovieCrew;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                PersonMapper.class,
                PositionMapper.class
        }
)
public interface MovieCrewMapper {

    MovieCrewResponse toMovieCrewResponse(MovieCrew movieCrew);

    List<MovieCrewResponse> toListMovieCrewResponse(List<MovieCrew> crew);

    MovieCrew toMovieCrew(MovieCrewCreationRequest request);

    List<MovieCrewCreationRequest> toListMovieCrew(List<MovieCrewCreationRequest> requests);

}
