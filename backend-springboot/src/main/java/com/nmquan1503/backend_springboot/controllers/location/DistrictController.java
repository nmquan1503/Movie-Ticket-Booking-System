package com.nmquan1503.backend_springboot.controllers.location;

import java.util.List;

import com.nmquan1503.backend_springboot.dtos.responses.location.DistrictODetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.services.location.DistrictService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/districts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DistrictController {
    
    DistrictService districtService;

    @GetMapping("/details/province/{provinceId}")
    ResponseEntity<ApiResponse<List<DistrictODetailResponse>>> getDistrictDetailsByProvinceId(
            @PathVariable Short provinceId
    ) {
        ApiResponse<List<DistrictODetailResponse>> response = ApiResponse.success(
                districtService.getDistrictDetailsByProvinceId(provinceId)
        );
        return ResponseEntity.ok().body(response);
    }
}
