package com.nmquan1503.backend_springboot.configurations;

import com.nmquan1503.backend_springboot.entities.location.Ward;
import com.nmquan1503.backend_springboot.services.authentication.RefreshTokenService;
import com.nmquan1503.backend_springboot.services.location.WardService;
import com.nmquan1503.backend_springboot.services.payment.PaymentStrategyFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PaymentStrategyFactory paymentStrategyFactory;

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {

        };
    }

}
