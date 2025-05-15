package com.nmquan1503.backend_springboot.repositories.showtime.custom.impl;

import com.nmquan1503.backend_springboot.repositories.showtime.custom.CustomShowtimeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomShowtimeRepositoryImpl implements CustomShowtimeRepository {

    JPAQueryFactory queryFactory;

}
