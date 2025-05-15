package com.nmquan1503.backend_springboot.mappers.user;

import com.nmquan1503.backend_springboot.dtos.responses.user.PermissionResponse;
import com.nmquan1503.backend_springboot.dtos.responses.user.RoleResponse;
import com.nmquan1503.backend_springboot.entities.user.Permission;
import com.nmquan1503.backend_springboot.entities.user.Role;
import com.nmquan1503.backend_springboot.entities.user.RolePermission;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true) // Tạm thời bỏ qua trường này
    RoleResponse toRoleResponse(Role role);

//    @AfterMapping
//    default void mapPermissions(Role role, @MappingTarget RoleResponse roleResponse) {
//        Set<RolePermission> rolePermissions = role.getRolePermissions();
//        if (rolePermissions != null) {
//            roleResponse.setPermissions(rolePermissions.stream()
//                    .map(rp -> PermissionResponse.builder()
//                            .id(rp.getPermission().getId())
//                            .name(rp.getPermission().getName())
//                            .build())
//                    .toList());
//        }
//    }

}
