package com.nmquan1503.backend_springboot.dtos.responses.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {

    Byte id;
    String name;
    String thumbnailURL;
    Double price;

}
