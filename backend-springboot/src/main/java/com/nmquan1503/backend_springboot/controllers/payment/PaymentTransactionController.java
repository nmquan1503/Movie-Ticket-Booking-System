package com.nmquan1503.backend_springboot.controllers.payment;

import com.nmquan1503.backend_springboot.dtos.requests.payment.PaymentTransactionCreationRequest;
import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.payment.PaymentRedirectResponse;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.services.payment.PaymentTransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment-transactions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentTransactionController {

    PaymentTransactionService paymentTransactionService;

    @PostMapping
    ResponseEntity<ApiResponse<PaymentRedirectResponse>> createPayment(
        HttpServletRequest httpServletRequest, 
        @RequestBody PaymentTransactionCreationRequest request
    ) {
        ApiResponse<PaymentRedirectResponse> response = ApiResponse.success(
                paymentTransactionService.processPayment(httpServletRequest, request)
        );
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/callback")
    ResponseEntity<ApiResponse<Void>> handleReturn(@RequestParam Map<String, String> params) {
        boolean success = paymentTransactionService.callbackHandler(params);
        if (success) {
            return ResponseEntity.ok().body(
                    ApiResponse.success(null)
            );
        }
        else {
            return ResponseEntity.badRequest().body(
                    ApiResponse.error(ResponseCode.PAYMENT_FAILED)
            );
        }
    }

}
