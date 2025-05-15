package com.nmquan1503.backend_springboot.repositories.product.custom.impl;

import com.nmquan1503.backend_springboot.entities.product.QBranchProduct;
import com.nmquan1503.backend_springboot.repositories.product.custom.CustomBranchProductRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomBranchProductRepositoryImpl implements CustomBranchProductRepository {

    JPAQueryFactory queryFactory;

    @Override
    public boolean existsByBranchIdAndProductIds(Short branchId, List<Byte> productIds) {
        QBranchProduct branchProduct = QBranchProduct.branchProduct;
        Long count = queryFactory
                .select(branchProduct.count())
                .from(branchProduct)
                .where(branchProduct.branch.id.eq(branchId)
                        .and(branchProduct.product.id.in(productIds)))
                .fetchOne();
        return count != null && count == productIds.size();
    }
}
