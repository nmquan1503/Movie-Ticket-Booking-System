package com.nmquan1503.backend_springboot.services.user;

import com.nmquan1503.backend_springboot.dtos.responses.user.PermissionResponse;
import com.nmquan1503.backend_springboot.entities.user.Permission;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.user.PermissionMapper;
import org.springframework.stereotype.Service;

import com.nmquan1503.backend_springboot.repositories.user.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public List<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll().stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public PermissionResponse getPermission(Short permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new GeneralException(ResponseCode.PERMISSION_NOT_FOUND));
        return permissionMapper.toPermissionResponse(permission);
    }


}
