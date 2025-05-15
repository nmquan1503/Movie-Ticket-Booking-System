package com.nmquan1503.backend_springboot.repositories.user.custom;

import com.nmquan1503.backend_springboot.dtos.responses.user.RoleResponse;
import com.nmquan1503.backend_springboot.entities.user.Role;
import com.nmquan1503.backend_springboot.entities.user.User;

import java.util.List;

public interface CustomRoleRepository {

    Role findRoleWithPermissionsById(Byte id);

    List<Role> findByUserId(Long userId);

}
