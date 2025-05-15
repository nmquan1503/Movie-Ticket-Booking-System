package com.nmquan1503.backend_springboot.repositories.location;

import java.util.List;

import com.nmquan1503.backend_springboot.repositories.location.custom.CustomWardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nmquan1503.backend_springboot.entities.location.Ward;

public interface WardRepository extends JpaRepository<Ward, Integer>, CustomWardRepository {
    
    List<Ward> findByDistrictId(Integer districtId);
    
}
