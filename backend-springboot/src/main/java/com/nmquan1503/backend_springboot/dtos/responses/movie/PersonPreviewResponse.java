package com.nmquan1503.backend_springboot.dtos.responses.movie;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonPreviewResponse {

    Long id;
    String name;
    String avatarURL;

}
