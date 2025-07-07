package com.nmquan1503.backend_springboot.dtos.responses.movie;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieDetailResponse {

    Long id;
    String title;
    String posterURL;
    String backdropURL;
    String trailerURL;
    String tagline;
    String overview;
    LocalDate releasedDate;
    Short duration;
    LanguageResponse originalLanguage;
    AgeRatingDetailResponse ageRating;
    Double averageRating;
    Long ratingCount;
    Page<MovieCastResponse> cast;
    Page<MovieCrewResponse> crew;
    List<CategoryResponse> categories;
    Page<MovieImageResponse> images;

}
