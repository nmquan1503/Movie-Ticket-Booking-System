package com.nmquan1503.backend_springboot.repositories.product;

import com.nmquan1503.backend_springboot.entities.product.BranchProduct;
import com.nmquan1503.backend_springboot.repositories.product.custom.CustomBranchProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchProductRepository extends JpaRepository<BranchProduct, Short>, CustomBranchProductRepository {
}
