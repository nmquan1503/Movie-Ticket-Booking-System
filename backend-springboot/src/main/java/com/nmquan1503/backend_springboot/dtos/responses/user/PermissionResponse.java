package com.nmquan1503.backend_springboot.dtos.responses.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionResponse {
    
    Short id;
    String name;
    String description;

}
