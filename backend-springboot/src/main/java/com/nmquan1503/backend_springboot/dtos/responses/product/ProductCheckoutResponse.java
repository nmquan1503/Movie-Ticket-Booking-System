package com.nmquan1503.backend_springboot.dtos.responses.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCheckoutResponse {

    Byte id;
    String name;
    Double price;

}
