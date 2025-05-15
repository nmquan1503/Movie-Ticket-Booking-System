package com.nmquan1503.backend_springboot.dtos.internal;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentCreationRequest {

    Long reservationId;
    long amount;
    LocalDateTime expireTime;
    String ipAddress;

}
