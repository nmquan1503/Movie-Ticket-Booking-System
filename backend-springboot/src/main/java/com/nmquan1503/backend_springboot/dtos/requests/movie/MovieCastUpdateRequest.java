package com.nmquan1503.backend_springboot.dtos.requests.movie;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieCastUpdateRequest {

    Long id;
    String character;
    Short order;

}
