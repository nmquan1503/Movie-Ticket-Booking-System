package com.nmquan1503.backend_springboot.mappers.movie;

import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieImageResponse;
import com.nmquan1503.backend_springboot.entities.movie.MovieImage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MovieImageMapper {

    MovieImageResponse toMovieImageResponse(MovieImage movieImage);

}
