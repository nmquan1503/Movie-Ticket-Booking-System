package com.nmquan1503.backend_springboot.dtos.requests.user;

import com.nmquan1503.backend_springboot.validators.NotEmptyIfNotNull;
import com.nmquan1503.backend_springboot.validators.Password;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    @NotEmptyIfNotNull(message = "FULLNAME_INVALID")
    @Pattern(regexp = "^[A-Za-zÀ-Ỹà-ỹ ]+$", message = "FULLNAME_INVALID")
    String fullName;

    String avatarURL;

    @Password
    String password;

    Byte genderId;

    Integer wardId;

    String specificAddress;

}
