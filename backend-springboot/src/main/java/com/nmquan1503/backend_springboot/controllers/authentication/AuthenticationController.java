package com.nmquan1503.backend_springboot.controllers.authentication;

import com.nimbusds.jose.JOSEException;
import com.nmquan1503.backend_springboot.dtos.requests.authentication.AuthenticationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.authentication.IntrospectRequest;
import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.authentication.AuthenticationResponse;
import com.nmquan1503.backend_springboot.dtos.responses.authentication.IntrospectResponse;
import com.nmquan1503.backend_springboot.services.authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ApiResponse<AuthenticationResponse> response = ApiResponse.success(authenticationService.authenticate(request, httpServletRequest, httpServletResponse));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/introspect")
    ResponseEntity<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request) throws JOSEException, ParseException {
        ApiResponse<IntrospectResponse> response = ApiResponse.success(authenticationService.introspect(request));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/refresh")
    ResponseEntity<ApiResponse<AuthenticationResponse>> refresh(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ApiResponse<AuthenticationResponse> response = ApiResponse.success(authenticationService.refresh(httpServletRequest, httpServletResponse));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/logout")
    ResponseEntity<ApiResponse<Object>> logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        authenticationService.logout(httpServletRequest, httpServletResponse);
        ApiResponse<Object> response = ApiResponse.success(null);
        return ResponseEntity.ok().body(response);
    }

}
