package com.nmquan1503.backend_springboot.controllers.location;

import java.util.List;

import com.nmquan1503.backend_springboot.dtos.responses.location.WardDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.services.location.WardService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/wards")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WardController {
    
    WardService wardService;

    @GetMapping("/details/district/{districtId}")
    ResponseEntity<ApiResponse<List<WardDetailResponse>>> getWardDetailsByDistrictId(
            @PathVariable Integer districtId
    ) {
        ApiResponse<List<WardDetailResponse>> response = ApiResponse.success(
                wardService.getWardDetailsByDistrictId(districtId)
        );
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/details")
    ResponseEntity<ApiResponse<List<WardDetailResponse>>> getAllWardDetails() {
        ApiResponse<List<WardDetailResponse>> response = ApiResponse.success(
            wardService.getAllWardDetails()
        );
        return ResponseEntity.ok().body(response);
    }

}
