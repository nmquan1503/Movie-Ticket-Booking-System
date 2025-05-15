package com.nmquan1503.backend_springboot.controllers.payment;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.payment.PaymentMethodDetailResponse;
import com.nmquan1503.backend_springboot.services.payment.PaymentMethodService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment-methods")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentMethodController {

    PaymentMethodService paymentMethodService;

    @GetMapping
    ResponseEntity<ApiResponse<List<PaymentMethodDetailResponse>>> getPaymentMethodDetails() {
        ApiResponse<List<PaymentMethodDetailResponse>> response = ApiResponse.success(
                paymentMethodService.getPaymentMethodDetails()
        );
        return ResponseEntity.ok().body(response);
    }

}
