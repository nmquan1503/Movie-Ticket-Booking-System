package com.nmquan1503.backend_springboot.mappers.movie;

import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieDetailResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MoviePreviewResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieListItemResponse;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                AgeRatingMapper.class
        }
)
public interface MovieMapper {

    MoviePreviewResponse toMoviePreviewResponse(Movie movie);

    MovieListItemResponse toMovieListItemResponse(Movie movie);

    MovieDetailResponse toMovieDetailResponse(Movie movie);

    Movie toMovie(MovieCreationRequest request);

    void updateMovie(@MappingTarget Movie movie, MovieUpdateRequest request);

}
