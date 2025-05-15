package com.nmquan1503.backend_springboot.repositories.product.custom;

import java.util.List;

public interface CustomBranchProductRepository {

    public boolean existsByBranchIdAndProductIds(Short branchId, List<Byte> productIds);

}
