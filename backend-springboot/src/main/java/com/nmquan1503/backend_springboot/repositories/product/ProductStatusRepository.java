package com.nmquan1503.backend_springboot.repositories.product;

import com.nmquan1503.backend_springboot.entities.product.Product;
import com.nmquan1503.backend_springboot.entities.product.ProductStatus;
import com.nmquan1503.backend_springboot.repositories.product.custom.CustomProductStatusRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStatusRepository extends JpaRepository<ProductStatus, Byte>, CustomProductStatusRepository {



}
