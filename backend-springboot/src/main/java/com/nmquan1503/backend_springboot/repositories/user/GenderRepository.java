package com.nmquan1503.backend_springboot.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nmquan1503.backend_springboot.entities.user.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Byte> {
    
}
