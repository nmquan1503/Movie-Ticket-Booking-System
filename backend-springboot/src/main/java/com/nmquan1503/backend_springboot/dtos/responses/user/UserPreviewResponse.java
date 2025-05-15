package com.nmquan1503.backend_springboot.dtos.responses.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPreviewResponse {

    Long id;
    String fullName;
    String avatarURL;

}
