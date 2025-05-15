package com.nmquan1503.backend_springboot.repositories.location;

import com.nmquan1503.backend_springboot.repositories.location.custom.CustomProvinceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nmquan1503.backend_springboot.entities.location.Province;

public interface ProvinceRepository extends JpaRepository<Province, Short>, CustomProvinceRepository {
    
}
