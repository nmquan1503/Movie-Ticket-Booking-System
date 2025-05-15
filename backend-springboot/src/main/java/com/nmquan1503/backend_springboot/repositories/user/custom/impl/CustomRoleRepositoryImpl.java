package com.nmquan1503.backend_springboot.repositories.user.custom.impl;

import com.nmquan1503.backend_springboot.entities.user.*;
import com.nmquan1503.backend_springboot.repositories.user.custom.CustomRoleRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomRoleRepositoryImpl implements CustomRoleRepository {

    JPAQueryFactory queryFactory;
    QRole role = QRole.role;
    QRolePermission rolePermission = QRolePermission.rolePermission;
    QPermission permission = QPermission.permission;

    @Override
    public Role findRoleWithPermissionsById(Byte id) {
//        Role role1 = queryFactory
//                .select(role)
//                .from(role)
//                .leftJoin(role.rolePermissions, rolePermission).fetchJoin()
//                .leftJoin(rolePermission.permission, permission).fetchJoin()
//                .where(role.id.eq(id))
//                .fetchOne();
//        return role1;
        return null;
    }

    public List<Role> findByUserId(Long userId) {
        QUser user = QUser.user;
        QUserRole userRole = QUserRole.userRole;
        return queryFactory
                .select(userRole.role)
                .from(userRole)
                .join(userRole.role, role)
                .where(userRole.user.id.eq(userId))
                .fetch();
    }
}
