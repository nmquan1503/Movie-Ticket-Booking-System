package com.nmquan1503.backend_springboot.services.payment;

import com.nmquan1503.backend_springboot.dtos.responses.payment.PaymentMethodDetailResponse;
import com.nmquan1503.backend_springboot.entities.payment.PaymentMethod;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.payment.PaymentMethodMapper;
import com.nmquan1503.backend_springboot.repositories.payment.PaymentMethodRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentMethodService {

    PaymentMethodRepository paymentMethodRepository;

    PaymentMethodMapper paymentMethodMapper;

    public List<PaymentMethodDetailResponse> getPaymentMethodDetails() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        return paymentMethods.stream().map(
                paymentMethodMapper::toPaymentMethodDetailResponse
        ).toList();
    }

    public PaymentMethod fetchById(Byte paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new GeneralException(ResponseCode.PAYMENT_METHOD_NOT_FOUND));
    }

}
