package com.nmquan1503.backend_springboot.repositories.user;

import com.nmquan1503.backend_springboot.entities.user.User;
import com.nmquan1503.backend_springboot.repositories.user.custom.CustomUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

    Optional<User> findByPhone(String phone);

    Optional<User> findByEmail(String email);

}
