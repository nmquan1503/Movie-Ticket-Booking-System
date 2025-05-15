package com.nmquan1503.backend_springboot.repositories.user;

import com.nmquan1503.backend_springboot.entities.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Short> {

}
