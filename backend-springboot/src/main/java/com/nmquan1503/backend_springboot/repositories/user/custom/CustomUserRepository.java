package com.nmquan1503.backend_springboot.repositories.user.custom;

import com.nmquan1503.backend_springboot.entities.user.User;

public interface CustomUserRepository {
    boolean existsByPhoneOrEmail(String identifier);
}
