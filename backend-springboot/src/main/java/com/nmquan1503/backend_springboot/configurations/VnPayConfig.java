package com.nmquan1503.backend_springboot.configurations;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class VnPayConfig {

    @Value("${vnpay.tmnCode}")
    String TMN_CODE;

    @Value("${vnpay.secretKey}")
    String SECRET_KEY;

    @Value("${vnpay.payUrl}")
    String PAY_URL;

    @Value("${vnpay.returnUrl}")
    String RETURN_URL;

    @Value("${vnpay.command}")
    String COMMAND;

    @Value("${vnpay.version}")
    String VERSION;

    public Map<String, String> getVnPayConfig() {
        Map<String, String> vnPayParams = new HashMap<>();
        vnPayParams.put("vnp_Version", VERSION);
        vnPayParams.put("vnp_Command", COMMAND);
        vnPayParams.put("vnp_TmnCode", TMN_CODE);
        vnPayParams.put("vnp_BankCode", "VNBANK");
        vnPayParams.put("vnp_CurrCode", "VND");
        vnPayParams.put("vnp_Locale", "vn");
        vnPayParams.put("vnp_OrderType", "other");
        vnPayParams.put("vnp_ReturnUrl", RETURN_URL);
        return vnPayParams;
    }

}
