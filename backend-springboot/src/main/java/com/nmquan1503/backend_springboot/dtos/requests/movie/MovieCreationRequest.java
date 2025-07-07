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

    String tagline;

    String overview;

    @NotNull(message = "RELEASED_DATE_MOVIE_EMPTY")
    LocalDate releasedDate;

    @NotNull(message = "DURATION_MOVIE_EMPTY")
    Short duration;

    @NotNull(message = "ORIGINAL_LANGUAGE_MOVIE_EMPTY")
    Byte originalLanguageId;

    Byte ageRatingId;

    @UniqueElements(message = "DUPLICATE_CAST_MEMBER")
    List<MovieCastCreationRequest> cast;

    @UniqueElements(message = "DUPLICATE_CREW_MEMBER")
    List<MovieCrewCreationRequest> crew;

    @UniqueElements(message = "DUPLICATE_CATEGORY")
    List<Byte> categoryIds;

}
