package com.nmquan1503.backend_springboot.dtos.internal;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieReviewStats {

    Long movieId;
    Double averageRating;
    Long countRating;

}
