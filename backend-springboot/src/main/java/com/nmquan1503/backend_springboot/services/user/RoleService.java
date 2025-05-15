package com.nmquan1503.backend_springboot.services.user;

import com.nmquan1503.backend_springboot.dtos.responses.user.RoleResponse;
import com.nmquan1503.backend_springboot.entities.user.Role;
import com.nmquan1503.backend_springboot.entities.user.User;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.user.RoleMapper;
import com.nmquan1503.backend_springboot.repositories.user.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;

//    public List<RoleResponse> getAllRoles() {
//        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
//    }
//
    public RoleResponse getRole(Byte roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseGet(null);
        RoleResponse response = new RoleResponse(role.getId(), role.getName(),null);

        return response;
    }

    public Role fetchRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new GeneralException(ResponseCode.ROLE_NOT_FOUND));
    }

    public List<Role> fetchRolesOfUserId(Long userId) {
        return roleRepository.findByUserId(userId);
    }

//
//    public Role fetchRoleById(Byte roleId) {
//        return roleRepository.findById(roleId)
//                .orElseThrow(() -> new GeneralException(ResponseCode.ROLE_NOT_FOUND));
//    }

}
