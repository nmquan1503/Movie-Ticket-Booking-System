package com.nmquan1503.backend_springboot.services.product;

import com.nmquan1503.backend_springboot.dtos.requests.product.BranchProductCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.product.BranchProductUpdateRequest;
import com.nmquan1503.backend_springboot.entities.product.BranchProduct;
import com.nmquan1503.backend_springboot.entities.product.Product;
import com.nmquan1503.backend_springboot.entities.product.ProductStatus;
import com.nmquan1503.backend_springboot.entities.theater.Branch;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.repositories.product.BranchProductRepository;
import com.nmquan1503.backend_springboot.services.theater.BranchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchProductService {

    BranchProductRepository branchProductRepository;

    BranchService branchService;
    ProductService productService;
    ProductStatusService productStatusService;

    public boolean existsByBranchIdAndProductIds(Short branchId, List<Byte> productIds) {
        return branchProductRepository.existsByBranchIdAndProductIds(branchId, productIds);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void createBranchProduct(BranchProductCreationRequest request) {
        if (!branchService.existsById(request.getBranchId())) {
            throw new GeneralException(ResponseCode.BRANCH_NOT_FOUND);
        }
        if (!productService.existsById(request.getProductId())) {
            throw new GeneralException(ResponseCode.PRODUCT_NOT_FOUND);
        }
        if (!productStatusService.existsById(request.getProductStatusId())) {
            throw new GeneralException(ResponseCode.PRODUCT_STATUS_NOT_FOUND);
        }
        BranchProduct branchProduct = BranchProduct.builder()
                .branch(Branch.builder().id(request.getBranchId()).build())
                .product(Product.builder().id(request.getProductId()).build())
                .status(ProductStatus.builder().id(request.getProductStatusId()).build())
                .build();
        branchProductRepository.save(branchProduct);
    }

    public BranchProduct fetchById(Short branchProductId) {
        return branchProductRepository.findById(branchProductId)
                .orElseThrow(() -> new GeneralException(ResponseCode.BRANCH_PRODUCT_NOT_FOUND));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void updateBranchProduct(Short branchProductId, BranchProductUpdateRequest request) {
        if (!productStatusService.existsById(request.getProductStatusId())) {
            throw new GeneralException(ResponseCode.PRODUCT_STATUS_NOT_FOUND);
        }
        BranchProduct branchProduct = fetchById(branchProductId);
        branchProduct.setStatus(ProductStatus.builder().id(request.getProductStatusId()).build());
        branchProductRepository.save(branchProduct);
    }

}
