package com.nmquan1503.backend_springboot.controllers.location;

import java.util.List;

import com.nmquan1503.backend_springboot.dtos.responses.location.ProvinceDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.services.location.ProvinceService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/provinces")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProvinceController {
    
    ProvinceService provinceService;

    @GetMapping("/details")
    ResponseEntity<ApiResponse<List<ProvinceDetailResponse>>> getAllProvinceDetails() {
        ApiResponse<List<ProvinceDetailResponse>> response = ApiResponse.success(
                provinceService.getAllProvinceDetails()
        );
        return ResponseEntity.ok().body(response);
    }

}
