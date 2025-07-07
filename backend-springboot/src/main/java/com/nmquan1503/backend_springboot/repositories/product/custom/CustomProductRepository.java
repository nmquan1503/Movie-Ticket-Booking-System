package com.nmquan1503.backend_springboot.repositories.product.custom;

import com.nmquan1503.backend_springboot.entities.product.Product;

import java.util.List;

public interface CustomProductRepository {
    List<Product> findByReservationId(Long reservationId);
    List<Product> findByBranchId(Short branchId);
}
