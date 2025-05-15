package com.nmquan1503.backend_springboot.dtos.responses.user;

import java.util.List;
import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    
    Byte id;
    String name;
    List<PermissionResponse> permissions;

}
