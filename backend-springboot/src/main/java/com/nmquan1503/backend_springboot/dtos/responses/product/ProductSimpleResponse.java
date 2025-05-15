package com.nmquan1503.backend_springboot.dtos.responses.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSimpleResponse {

    Byte id;
    String name;

}
