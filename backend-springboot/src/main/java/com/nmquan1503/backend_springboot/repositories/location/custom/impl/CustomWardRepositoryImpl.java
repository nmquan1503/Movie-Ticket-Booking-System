package com.nmquan1503.backend_springboot.repositories.location.custom.impl;

import com.nmquan1503.backend_springboot.repositories.location.custom.CustomWardRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomWardRepositoryImpl implements CustomWardRepository {

    JPAQueryFactory queryFactory;

}
