package com.nmquan1503.backend_springboot.repositories.payment.custom.impl;

import com.nmquan1503.backend_springboot.entities.payment.QPaymentMethod;
import com.nmquan1503.backend_springboot.repositories.payment.custom.CustomPaymentMethodRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomPaymentMethodRepositoryImpl implements CustomPaymentMethodRepository {

    JPAQueryFactory queryFactory;

}
