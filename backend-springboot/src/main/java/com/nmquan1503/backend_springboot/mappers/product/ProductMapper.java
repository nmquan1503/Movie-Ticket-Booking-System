package com.nmquan1503.backend_springboot.mappers.product;

import com.nmquan1503.backend_springboot.dtos.requests.product.ProductCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.product.ProductUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.product.ProductCheckoutResponse;
import com.nmquan1503.backend_springboot.dtos.responses.product.ProductReservationItemResponse;
import com.nmquan1503.backend_springboot.dtos.responses.product.ProductSimpleResponse;
import com.nmquan1503.backend_springboot.entities.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {

    ProductCheckoutResponse toProductCheckoutResponse(Product product);

    ProductSimpleResponse toProductSimpleResponse(Product product);

    Product toProduct(ProductCreationRequest request);

    void update(@MappingTarget Product product, ProductUpdateRequest request);

}
