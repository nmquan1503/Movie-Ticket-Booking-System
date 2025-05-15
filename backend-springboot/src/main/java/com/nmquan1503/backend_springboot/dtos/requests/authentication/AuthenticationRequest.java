package com.nmquan1503.backend_springboot.dtos.requests.authentication;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {

    @NotNull(message = "EMAIL_OR_PHONE_EMPTY")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$|^(0|\\+84|84)\\d{9}$", message = "INVALID_EMAIL_OR_PHONE")
    String identifier;

    @NotNull(message = "PASSWORD_EMPTY")
    String password;

}
