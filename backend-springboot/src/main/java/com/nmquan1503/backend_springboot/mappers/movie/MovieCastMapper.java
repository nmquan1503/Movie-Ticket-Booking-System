package com.nmquan1503.backend_springboot.mappers.movie;

import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieCastCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieCastUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieCastResponse;
import com.nmquan1503.backend_springboot.entities.movie.MovieCast;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.lang.annotation.Target;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                PersonMapper.class
        }
)
public interface MovieCastMapper {

    MovieCastResponse toMovieCastResponse(MovieCast movieCast);

    List<MovieCastResponse> toListMovieCastResponse(List<MovieCast> cast);

    MovieCast toMovieCast(MovieCastCreationRequest request);

    List<MovieCast> toListMovieCast(List<MovieCastCreationRequest> requests);

    @Mapping(target = "id", ignore = true)
    void updateMovieCast(@MappingTarget MovieCast movieCast, MovieCastUpdateRequest request);

}
