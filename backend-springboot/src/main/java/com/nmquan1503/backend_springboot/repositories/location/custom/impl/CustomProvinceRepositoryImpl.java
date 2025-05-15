package com.nmquan1503.backend_springboot.repositories.location.custom.impl;

import com.nmquan1503.backend_springboot.repositories.location.custom.CustomProvinceRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomProvinceRepositoryImpl implements CustomProvinceRepository {

    JPAQueryFactory queryFactory;

}
