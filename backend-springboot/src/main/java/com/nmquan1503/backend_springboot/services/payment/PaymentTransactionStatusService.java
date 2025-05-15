package com.nmquan1503.backend_springboot.services.payment;

import com.nmquan1503.backend_springboot.entities.payment.PaymentTransactionStatus;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.repositories.payment.PaymentTransactionStatusRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentTransactionStatusService {

    PaymentTransactionStatusRepository paymentTransactionStatusRepository;

    PaymentTransactionStatus fetchByName(String name) {
        return paymentTransactionStatusRepository.findByName(name)
                .orElseThrow(() -> new GeneralException(ResponseCode.PAYMENT_TRANSACTION_STATUS_NOT_FOUND));
    }

}
