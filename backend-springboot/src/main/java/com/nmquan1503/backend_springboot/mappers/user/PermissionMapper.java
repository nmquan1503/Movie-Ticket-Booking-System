package com.nmquan1503.backend_springboot.mappers.user;

import com.nmquan1503.backend_springboot.dtos.responses.user.PermissionResponse;
import com.nmquan1503.backend_springboot.entities.user.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionResponse toPermissionResponse(Permission permission);

}
