package com.nmquan1503.backend_springboot.services.user;

import com.nmquan1503.backend_springboot.entities.user.Role;
import com.nmquan1503.backend_springboot.entities.user.User;
import com.nmquan1503.backend_springboot.entities.user.UserRole;
import com.nmquan1503.backend_springboot.repositories.user.UserRoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRoleService {

    UserRoleRepository userRoleRepository;
    RoleService roleService;

    UserRole saveWithRoleName(User user, String roleName) {
        UserRole userRole = UserRole.builder()
                .user(user)
                .role(roleService.fetchRoleByName(roleName))
                .build();
        return userRoleRepository.save(userRole);
    }

}
