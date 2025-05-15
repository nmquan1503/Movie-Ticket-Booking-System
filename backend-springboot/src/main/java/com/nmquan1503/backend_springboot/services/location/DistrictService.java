package com.nmquan1503.backend_springboot.services.location;

import java.util.List;

import com.nmquan1503.backend_springboot.dtos.responses.location.DistrictODetailResponse;
import org.springframework.stereotype.Service;

import com.nmquan1503.backend_springboot.entities.location.District;
import com.nmquan1503.backend_springboot.mappers.location.DistrictMapper;
import com.nmquan1503.backend_springboot.repositories.location.DistrictRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DistrictService {
    
    DistrictRepository districtRepository;

    DistrictMapper districtMapper;

    public List<DistrictODetailResponse> getDistrictDetailsByProvinceId(Short provinceId) {
        List<District> districts = districtRepository.findByProvinceId(provinceId);
        return districts.stream().map(
                districtMapper::toDistrictDetailResponse
        ).toList();
    }

}
