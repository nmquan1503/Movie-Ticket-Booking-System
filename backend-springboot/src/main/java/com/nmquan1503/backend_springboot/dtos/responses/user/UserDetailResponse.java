package com.nmquan1503.backend_springboot.dtos.responses.user;

import com.nmquan1503.backend_springboot.dtos.responses.location.WardDetailResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailResponse {

    Long id;
    String fullName;
    String avatarURL;
    String phone;
    String email;
    LocalDateTime birthday;
    GenderOptionResponse gender;
    WardDetailResponse ward;
    String specificAddress;

}
