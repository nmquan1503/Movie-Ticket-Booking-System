package com.nmquan1503.backend_springboot.services.payment;

import com.nmquan1503.backend_springboot.configurations.VnPayConfig;
import com.nmquan1503.backend_springboot.dtos.internal.PaymentCreationRequest;
import com.nmquan1503.backend_springboot.dtos.internal.PaymentCallbackResult;
import com.nmquan1503.backend_springboot.dtos.internal.PaymentInitResult;
import com.nmquan1503.backend_springboot.entities.payment.PaymentMethod;
import com.nmquan1503.backend_springboot.entities.payment.PaymentTransaction;
import com.nmquan1503.backend_springboot.entities.reservation.Reservation;
import com.nmquan1503.backend_springboot.utils.VnPayUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VnPayPaymentStrategy implements PaymentStrategy{

    VnPayConfig vnPayConfig;

    PaymentTransactionStatusService paymentTransactionStatusService;

    @Override
    public PaymentInitResult processPayment(PaymentCreationRequest request) {
        Map<String, String> params = vnPayConfig.getVnPayConfig();

        long amount = request.getAmount() * 100L;
        params.put("vnp_Amount", String.valueOf(amount));

        String transactionId = VnPayUtil.generateTransactionId();
        params.put("vnp_TxnRef", transactionId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createTime = LocalDateTime.now();
        String formattedCreateDate = createTime.format(formatter);
        String formattedExpireDate = request.getExpireTime().format(formatter);
        params.put("vnp_CreateDate", formattedCreateDate);
        params.put("vnp_ExpireDate", formattedExpireDate);

        params.put("vnp_IpAddr", request.getIpAddress());

        params.put("vnp_OrderInfo", "Thanh toan cho don hang " + transactionId);

        String queryURL = VnPayUtil.getPaymentURL(params, true);
        String hashData = VnPayUtil.getPaymentURL(params, false);
        String vnpSecureHash = VnPayUtil.hmacSHA512(vnPayConfig.getSECRET_KEY(), hashData);
        queryURL += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentURL = vnPayConfig.getPAY_URL() + "?" + queryURL;

        PaymentTransaction paymentTransaction = PaymentTransaction.builder()
                .transactionId(transactionId)
                .reservation(Reservation.builder().id(request.getReservationId()).build())
                .amount((double) request.getAmount())
                .time(LocalDateTime.now())
                .method(PaymentMethod.builder().id(this.getId()).build())
                .status(paymentTransactionStatusService.fetchByName("PENDING"))
                .build();

        return PaymentInitResult.builder()
                .paymentTransaction(paymentTransaction)
                .paymentURL(paymentURL)
                .build();
    }

    @Override
    public PaymentCallbackResult handleCallback(Map<String, String> params) {
        String receivedHash = params.remove("vnp_SecureHash");
        String hashData = VnPayUtil.getPaymentURL(params, false);
        String calculateHash = VnPayUtil.hmacSHA512(vnPayConfig.getSECRET_KEY(), hashData);
        String responseCode = params.get("vnp_ResponseCode");
        if (!calculateHash.equalsIgnoreCase(receivedHash) || !"00".equals(responseCode)) {
            return PaymentCallbackResult.builder()
                    .success(false)
                    .build();
        }
        return PaymentCallbackResult.builder()
                .transactionId(params.get("vnp_TxnRef"))
                .success(true)
                .build();
    }

    @Override
    public Byte getId() {
        return 3;
    }

    @Override
    public String getName() {
        return "VNPay";
    }
}
