package com.nmquan1503.backend_springboot.dtos.responses.payment;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentMethodDetailResponse {

    Byte id;
    String name;
    String thumbnailURL;
    PaymentMethodStatusResponse status;

}
