package com.nmquan1503.backend_springboot.repositories.user.custom.impl;

import com.nmquan1503.backend_springboot.entities.user.QUser;
import com.nmquan1503.backend_springboot.repositories.user.custom.CustomUserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    JPAQueryFactory queryFactory;

    @Override
    public boolean existsByPhoneOrEmail(String identifier) {
/*        QUser user = QUser.user;
        return queryFactory
                .select(user)
                .from(user)
                .where(user.phone.eq(identifier).or(user.email.eq(identifier)))
                .fetchOne() != null;*/
        return false;
    }
}
