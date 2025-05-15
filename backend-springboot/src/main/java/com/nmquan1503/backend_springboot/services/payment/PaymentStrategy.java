package com.nmquan1503.backend_springboot.services.payment;

import com.nmquan1503.backend_springboot.dtos.internal.PaymentCreationRequest;
import com.nmquan1503.backend_springboot.dtos.internal.PaymentCallbackResult;
import com.nmquan1503.backend_springboot.dtos.internal.PaymentInitResult;

import java.util.Map;

public interface PaymentStrategy {

    PaymentInitResult processPayment(PaymentCreationRequest request);
    PaymentCallbackResult handleCallback(Map<String, String> params);
    Byte getId();
    String getName();

}
