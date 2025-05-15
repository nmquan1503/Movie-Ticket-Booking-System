package com.nmquan1503.backend_springboot.dtos.requests.product;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOrderRequest {

    @NotNull(message = "PRODUCT_EMPTY")
    Byte productId;

    @NotNull(message = "PRODUCT_QUANTITY_EMPTY")
    Byte quantity;

}
