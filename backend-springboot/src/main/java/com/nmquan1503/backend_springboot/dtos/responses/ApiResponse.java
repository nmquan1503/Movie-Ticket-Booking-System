package com.nmquan1503.backend_springboot.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    int code;
    boolean success;
    String message;
    T data;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
            .code(ResponseCode.SUCCESS.getCode())
            .success(true)
            .message(ResponseCode.SUCCESS.getMessage())
            .data(data)
            .build();
    }

    public static <T> ApiResponse<T> error(ResponseCode responseCode) {
        return ApiResponse.<T>builder()
                .code(responseCode.getCode())
                .success(false)
                .message(responseCode.getMessage())
                .build();
    }

}
