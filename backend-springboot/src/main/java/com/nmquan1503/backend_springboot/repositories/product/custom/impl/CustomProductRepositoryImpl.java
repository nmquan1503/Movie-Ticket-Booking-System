package com.nmquan1503.backend_springboot.repositories.product.custom.impl;

import com.nmquan1503.backend_springboot.entities.product.Product;
import com.nmquan1503.backend_springboot.entities.product.QBranchProduct;
import com.nmquan1503.backend_springboot.entities.product.QProduct;
import com.nmquan1503.backend_springboot.entities.product.QProductStatus;
import com.nmquan1503.backend_springboot.entities.reservation.QReservationProduct;
import com.nmquan1503.backend_springboot.entities.theater.QBranch;
import com.nmquan1503.backend_springboot.repositories.product.custom.CustomProductRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomProductRepositoryImpl implements CustomProductRepository {

    JPAQueryFactory queryFactory;

    @Override
    public List<Product> findByReservationId(Long reservationId) {
        QProduct product = QProduct.product;
        QReservationProduct reservationProduct = QReservationProduct.reservationProduct;

        return queryFactory
                .select(product)
                .from(product)
                .join(product, reservationProduct.product)
                .where(reservationProduct.reservation.id.eq(reservationId))
                .fetch();
    }

    @Override
    public List<Product> findByBranchId(Short branchId) {
        QProduct product = QProduct.product;
        QBranchProduct branchProduct = QBranchProduct.branchProduct;
        QProductStatus productStatus = QProductStatus.productStatus;

        return queryFactory
                .select(product)
                .from(product)
                .join(branchProduct).on(branchProduct.product.id.eq(product.id))
                .join(productStatus).on(productStatus.id.eq(branchProduct.status.id))
                .on(branchProduct.branch.id.eq(branchId)
                        .and(productStatus.name.eq("ACTIVE")))
                .fetch();
    }
}
