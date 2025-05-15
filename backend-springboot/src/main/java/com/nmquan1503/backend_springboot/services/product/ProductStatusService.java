package com.nmquan1503.backend_springboot.services.product;

import com.nmquan1503.backend_springboot.repositories.product.ProductStatusRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductStatusService {

    ProductStatusRepository productStatusRepository;

    public boolean checkAllProductStatusIdsExist(List<Byte> productStatusIds) {
        return productStatusRepository.checkAllProductStatusIdExist(productStatusIds);
    }

    public boolean existsById(Byte productStatusId) {
        return productStatusRepository.existsById(productStatusId);
    }

}
