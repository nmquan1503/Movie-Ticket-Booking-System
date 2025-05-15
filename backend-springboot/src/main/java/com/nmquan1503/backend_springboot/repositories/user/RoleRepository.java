package com.nmquan1503.backend_springboot.repositories.user;

import com.nmquan1503.backend_springboot.entities.user.User;
import com.nmquan1503.backend_springboot.repositories.user.custom.CustomRoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nmquan1503.backend_springboot.entities.user.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Byte>, CustomRoleRepository {

    Optional<Role> findByName(String name);

}
