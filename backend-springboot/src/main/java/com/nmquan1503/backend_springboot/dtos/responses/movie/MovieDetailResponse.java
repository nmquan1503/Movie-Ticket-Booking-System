package com.nmquan1503.backend_springboot.dtos.responses.movie;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String bannerURL;
    String trailerURL;
    String overview;
    LocalDate releasedDate;
    Short duration;
    LanguageResponse originalLanguage;
    LanguageResponse subtitleLanguage;
    AgeRatingDetailResponse ageRating;
    Double averageRating;
    Integer ratingCount;
    List<PersonPreviewResponse> actors;
    List<PersonPreviewResponse> directors;
    List<CategoryResponse> categories;

}
