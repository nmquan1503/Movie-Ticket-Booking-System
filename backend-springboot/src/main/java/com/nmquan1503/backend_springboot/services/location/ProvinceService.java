package com.nmquan1503.backend_springboot.services.location;

import java.util.List;

import com.nmquan1503.backend_springboot.dtos.responses.location.ProvinceDetailResponse;
import org.springframework.stereotype.Service;

import com.nmquan1503.backend_springboot.entities.location.Province;
import com.nmquan1503.backend_springboot.mappers.location.ProvinceMapper;
import com.nmquan1503.backend_springboot.repositories.location.ProvinceRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProvinceService {
    
    ProvinceRepository provinceRepository;

    ProvinceMapper provinceMapper;

    public List<ProvinceDetailResponse> getAllProvinceDetails() {
        List<Province> provinces = provinceRepository.findAll();
        return provinces.stream().map(
                provinceMapper::toProvinceDetailResponse
        ).toList();
    }

}
