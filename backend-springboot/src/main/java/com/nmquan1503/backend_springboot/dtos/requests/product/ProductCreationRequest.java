package com.nmquan1503.backend_springboot.dtos.requests.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {

    @NotBlank(message = "PRODUCT_NAME_EMPTY")
    String name;

    @NotBlank(message = "PRODUCT_THUMBNAIL_EMPTY")
    String thumbnailURL;

    @NotNull(message = "PRODUCT_PRICE_EMPTY")
    Double price;

}
