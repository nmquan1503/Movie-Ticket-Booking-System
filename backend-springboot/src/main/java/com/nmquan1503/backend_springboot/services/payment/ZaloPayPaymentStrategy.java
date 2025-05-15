package com.nmquan1503.backend_springboot.services.payment;

import com.nmquan1503.backend_springboot.dtos.internal.PaymentCreationRequest;
import com.nmquan1503.backend_springboot.dtos.internal.PaymentCallbackResult;
import com.nmquan1503.backend_springboot.dtos.internal.PaymentInitResult;

import java.util.Map;

public class ZaloPayPaymentStrategy implements PaymentStrategy{
    @Override
    public PaymentInitResult processPayment(PaymentCreationRequest request) {
        return null;
    }

    @Override
    public PaymentCallbackResult handleCallback(Map<String, String> params) {
        return null;
    }

    @Override
    public Byte getId() {
        return 2;
    }

    @Override
    public String getName() {
        return "ZaloPay";
    }
}
