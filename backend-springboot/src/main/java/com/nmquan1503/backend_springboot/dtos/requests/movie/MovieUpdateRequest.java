package com.nmquan1503.backend_springboot.dtos.requests.movie;

import com.nmquan1503.backend_springboot.validators.UniqueElements;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieUpdateRequest {

    String title;
    String posterURL;
    String bannerURL;
    String trailerURL;
    String overview;
    LocalDate releasedDate;
    Short duration;
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
