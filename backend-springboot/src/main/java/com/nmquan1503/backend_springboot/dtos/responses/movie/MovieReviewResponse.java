package com.nmquan1503.backend_springboot.dtos.responses.movie;

import com.nmquan1503.backend_springboot.dtos.responses.user.UserPreviewResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieReviewResponse {

    Long id;
    UserPreviewResponse user;
    Byte rating;
    String comment;
    LocalDateTime creationTime;

}
