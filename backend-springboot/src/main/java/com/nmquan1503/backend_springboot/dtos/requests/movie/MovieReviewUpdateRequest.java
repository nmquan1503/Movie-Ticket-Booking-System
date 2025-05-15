package com.nmquan1503.backend_springboot.dtos.requests.movie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieReviewUpdateRequest {

    @NotNull(message = "RATING_REVIEW_EMPTY")
    Byte rating;

    @NotBlank(message = "COMMENT_REVIEW_EMPTY")
    String comment;

}
