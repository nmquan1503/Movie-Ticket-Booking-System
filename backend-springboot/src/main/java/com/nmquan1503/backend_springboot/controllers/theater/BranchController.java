package com.nmquan1503.backend_springboot.controllers.theater;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.BranchOptionResponse;
import com.nmquan1503.backend_springboot.services.theater.BranchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/branches")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchController {

    BranchService branchService;

    @GetMapping("/summary/province/{provinceId}")
    ResponseEntity<ApiResponse<List<BranchOptionResponse>>> getBranchSummariesByProvinceId(
            @PathVariable Short provinceId
    ) {
        ApiResponse<List<BranchOptionResponse>> response = ApiResponse.success(branchService.getBranchSummariesByProvinceId(provinceId));
        return ResponseEntity.ok().body(response);
    }

}
