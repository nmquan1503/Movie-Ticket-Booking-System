package com.nmquan1503.backend_springboot.dtos.requests.user;

import com.nmquan1503.backend_springboot.validators.Password;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @NotNull(message = "FULLNAME_EMPTY")
    @NotBlank(message = "FULLNAME_EMPTY")
    @Pattern(regexp = "^[a-zA-ZÀ-ỹ ]+$", message = "FULLNAME_INVALID")
//    @Pattern(regexp = "^[a-zA-Z_ÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\ ]+$", message = "FULLNAME_INVALID")
    String fullName;

    @NotNull(message = "PHONE_EMPTY")
    @NotBlank(message = "PHONE_EMPTY")
    @Pattern(regexp = "^(\\+84|0)\\d{9}$", message = "PHONE_INVALID")
    String phone;

    @NotNull(message = "EMAIL_EMPTY")
    @NotBlank(message = "EMAIL_EMPTY")
    @Email(message = "EMAIL_INVALID")
    String email;

    @NotNull(message = "PASSWORD_EMPTY")
    @Password
    String password;

    @NotNull(message = "BIRTHDAY_EMPTY")
    @PastOrPresent(message = "BIRTHDAY_INVALID")
    LocalDate birthday;

    @NotNull(message = "GENDER_EMPTY")
    Byte genderId;

    @NotNull(message = "ADDRESS_EMPTY")
    Integer wardId;

    String specificAddress;

}
