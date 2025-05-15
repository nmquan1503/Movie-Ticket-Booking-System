package com.nmquan1503.backend_springboot.services.payment;

import com.nmquan1503.backend_springboot.entities.payment.PaymentMethod;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentStrategyFactory {

    Map<Byte, PaymentStrategy> paymentStrategyMap;

    PaymentMethodService paymentMethodService;

    PaymentStrategyFactory(
            List<PaymentStrategy> paymentStrategies,
            PaymentMethodService paymentMethodService
    ) {
        this.paymentMethodService = paymentMethodService;
        this.paymentStrategyMap = new HashMap<>();
        for (PaymentStrategy paymentStrategy : paymentStrategies) {
            paymentStrategyMap.put(paymentStrategy.getId(), paymentStrategy);
        }
    }

    public PaymentStrategy getPaymentStrategy(Byte paymentMethodId) {
        if (!paymentStrategyMap.containsKey(paymentMethodId)) {
            throw new GeneralException(ResponseCode.PAYMENT_METHOD_NOT_FOUND);
        }
        PaymentMethod paymentMethod = paymentMethodService.fetchById(paymentMethodId);
        if (paymentMethod.getStatus().getName().equals("MAINTENANCE")) {
            throw new GeneralException(ResponseCode.PAYMENT_METHOD_MAINTENANCE);
        }
        else if (paymentMethod.getStatus().getName().equals("DISABLED")) {
            throw new GeneralException(ResponseCode.PAYMENT_METHOD_DISABLED);
        }
        return paymentStrategyMap.get(paymentMethodId);
    }

    public PaymentStrategy detectPaymentStrategy(Map<String, String> params) {
        if (params.containsKey("vnp_TransactionNo")) {
            return paymentStrategyMap.get((byte) 3);
        }
        else throw new GeneralException(ResponseCode.PAYMENT_METHOD_NOT_FOUND);
    }

}
