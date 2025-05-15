package com.nmquan1503.backend_springboot.controllers.user;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.user.RoleResponse;
import com.nmquan1503.backend_springboot.services.user.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleService roleService;

//    @GetMapping
//    ResponseEntity<ApiResponse<List<RoleResponse>>> getAllRoles() {
//        ApiResponse<List<RoleResponse>> response = ApiResponse.success(roleService.getAllRoles());
//        return ResponseEntity.ok().body(response);
//    }
//
    @GetMapping("/{roleId}")
    ResponseEntity<ApiResponse<RoleResponse>> getRole(@PathVariable Byte roleId) {
        ApiResponse<RoleResponse> response = ApiResponse.success(roleService.getRole(roleId));
        return ResponseEntity.ok().body(response);
    }

}
