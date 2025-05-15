package com.nmquan1503.backend_springboot.mappers.payment;

import com.nmquan1503.backend_springboot.dtos.responses.payment.PaymentMethodDetailResponse;
import com.nmquan1503.backend_springboot.entities.payment.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PaymentMethodMapper {

    PaymentMethodDetailResponse toPaymentMethodDetailResponse(PaymentMethod paymentMethod);

}
