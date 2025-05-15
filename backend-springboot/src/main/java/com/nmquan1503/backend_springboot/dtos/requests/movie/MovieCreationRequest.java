package com.nmquan1503.backend_springboot.dtos.requests.movie;

import com.nmquan1503.backend_springboot.validators.UniqueElements;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieCreationRequest {

    @NotBlank(message = "TITLE_MOVIE_EMPTY")
    String title;

    @NotBlank(message = "POSTER_MOVIE_EMPTY")
    String posterURL;

    String bannerURL;

    String trailerURL;

    String overview;

    @NotNull(message = "RELEASED_DATE_MOVIE_EMPTY")
    LocalDate releasedDate;

    @NotNull(message = "DURATION_MOVIE_EMPTY")
    Short duration;

    @NotNull(message = "ORIGINAL_LANGUAGE_MOVIE_EMPTY")
    Byte originalLanguageId;

    Byte subtitleLanguageId;

    Byte ageRatingId;

    @UniqueElements(message = "DUPLICATE_ACTOR")
    List<Long> actorIds;

    @UniqueElements(message = "DUPLICATE_DIRECTOR")
    List<Long> directorIds;

    @UniqueElements(message = "DUPLICATE_CATEGORY")
    List<Byte> categoryIds;

}
