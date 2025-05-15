package com.nmquan1503.backend_springboot.repositories.product;

import com.nmquan1503.backend_springboot.entities.product.Product;
import com.nmquan1503.backend_springboot.repositories.product.custom.CustomProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Byte>, CustomProductRepository {
}
