package com.nmquan1503.backend_springboot.controllers.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.user.GenderOptionResponse;
import com.nmquan1503.backend_springboot.services.user.GenderService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/genders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenderController {
    
    GenderService genderService;

    @GetMapping("/options")
    ResponseEntity<ApiResponse<List<GenderOptionResponse>>> getAllGenderOptions() {
        ApiResponse<List<GenderOptionResponse>> response = ApiResponse.success(genderService.getAllGenderOptions());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{genderId}")
    ResponseEntity<ApiResponse<GenderOptionResponse>> getGender(@PathVariable Byte genderId) {
        ApiResponse<GenderOptionResponse> response = ApiResponse.success(genderService.getGenderOptionByGenderId(genderId));
        return ResponseEntity.ok().body(response);
    }

}
