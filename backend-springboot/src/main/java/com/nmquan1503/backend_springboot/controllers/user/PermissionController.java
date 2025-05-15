package com.nmquan1503.backend_springboot.controllers.user;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.user.PermissionResponse;
import com.nmquan1503.backend_springboot.services.user.PermissionService;
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
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {

    PermissionService permissionService;

    @GetMapping
    ResponseEntity<ApiResponse<List<PermissionResponse>>> getAllPermissions() {
        ApiResponse<List<PermissionResponse>> response = ApiResponse.success(permissionService.getAllPermissions());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{permissionId}")
    ResponseEntity<ApiResponse<PermissionResponse>> getPermission(@PathVariable Short permissionId) {
        ApiResponse<PermissionResponse> response = ApiResponse.success(permissionService.getPermission(permissionId));
        return ResponseEntity.ok().body(response);
    }

}
