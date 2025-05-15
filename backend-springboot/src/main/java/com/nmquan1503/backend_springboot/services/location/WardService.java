package com.nmquan1503.backend_springboot.services.location;

import java.util.List;

import com.nmquan1503.backend_springboot.dtos.responses.location.WardDetailResponse;
import org.springframework.stereotype.Service;

import com.nmquan1503.backend_springboot.entities.location.Ward;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.location.WardMapper;
import com.nmquan1503.backend_springboot.repositories.location.WardRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WardService {
    
    WardRepository wardRepository;

    WardMapper wardMapper;


    public Ward fetchWardById(Integer wardId) {
        return wardRepository.findById(wardId)
                .orElseThrow(() -> new GeneralException(ResponseCode.WARD_NOT_FOUND));
    }

    public List<WardDetailResponse> getWardDetailsByDistrictId(Integer districtId) {
        List<Ward> wards = wardRepository.findByDistrictId(districtId);
        return wards.stream().map(
                wardMapper::toWardDetailResponse
        ).toList();
    }

}
