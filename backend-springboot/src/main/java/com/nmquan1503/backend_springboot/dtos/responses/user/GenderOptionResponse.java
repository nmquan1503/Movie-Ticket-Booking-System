package com.nmquan1503.backend_springboot.dtos.responses.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenderOptionResponse {
    
    Byte id;
    String name;

}
