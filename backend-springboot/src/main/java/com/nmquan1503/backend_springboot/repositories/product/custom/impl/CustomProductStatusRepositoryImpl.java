package com.nmquan1503.backend_springboot.repositories.product.custom.impl;

import com.nmquan1503.backend_springboot.entities.product.QProductStatus;
import com.nmquan1503.backend_springboot.repositories.product.custom.CustomProductStatusRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomProductStatusRepositoryImpl implements CustomProductStatusRepository {

    JPAQueryFactory queryFactory;

    @Override
    public boolean checkAllProductStatusIdExist(List<Byte> productStatusIds) {
        QProductStatus productStatus = QProductStatus.productStatus;
        Long count = queryFactory
                .select(productStatus.count())
                .from(productStatus)
                .where(productStatus.id.in(productStatusIds))
                .fetchOne();
        return count != null && count == productStatusIds.size();
    }
}
