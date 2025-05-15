package com.nmquan1503.backend_springboot.mappers.movie;

import com.nmquan1503.backend_springboot.dtos.responses.movie.AgeRatingLabelResponse;
import com.nmquan1503.backend_springboot.entities.movie.AgeRating;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AgeRatingMapper {

    AgeRatingLabelResponse toAgeRatingSummaryResponse(AgeRating ageRating);

}
