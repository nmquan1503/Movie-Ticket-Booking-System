package com.nmquan1503.backend_springboot.dtos.internal;

import com.nmquan1503.backend_springboot.entities.payment.PaymentTransaction;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentInitResult {

    PaymentTransaction paymentTransaction;
    String paymentURL;

}
