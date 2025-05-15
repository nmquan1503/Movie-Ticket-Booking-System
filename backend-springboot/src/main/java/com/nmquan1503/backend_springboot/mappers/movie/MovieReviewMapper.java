package com.nmquan1503.backend_springboot.mappers.movie;

import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieReviewCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieReviewUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieReviewResponse;
import com.nmquan1503.backend_springboot.entities.movie.MovieReview;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {PersonMapper.class}
)
public interface MovieReviewMapper {

    MovieReviewResponse toMovieReviewSummaryResponse(MovieReview movieReview);

    MovieReview toMovieReview(MovieReviewCreationRequest request);

    void updateReview(@MappingTarget MovieReview review, MovieReviewUpdateRequest request);

    MovieReview clone(MovieReview review);

}
