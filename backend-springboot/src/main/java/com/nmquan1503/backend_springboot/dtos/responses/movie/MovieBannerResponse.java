package com.nmquan1503.backend_springboot.dtos.responses.movie;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieBannerResponse {

    Long id;
    String title;
    String backdropURL;

}
