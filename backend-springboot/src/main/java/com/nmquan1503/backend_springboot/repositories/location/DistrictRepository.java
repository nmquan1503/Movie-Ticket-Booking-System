package com.nmquan1503.backend_springboot.repositories.location;

import java.util.List;

import com.nmquan1503.backend_springboot.repositories.location.custom.CustomDistrictRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nmquan1503.backend_springboot.entities.location.District;

public interface DistrictRepository extends JpaRepository<District, Integer>, CustomDistrictRepository {
    
    List<District> findByProvinceId(Short provinceId);

}
