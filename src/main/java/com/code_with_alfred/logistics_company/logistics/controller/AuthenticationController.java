package com.code_with_alfred.logistics_company.logistics.controller;


import com.code_with_alfred.logistics_company.logistics.dto.request.AuthenticationRequest;
import com.code_with_alfred.logistics_company.logistics.dto.request.RegisterRequest;
import com.code_with_alfred.logistics_company.logistics.dto.response.ApiResponse;
import com.code_with_alfred.logistics_company.logistics.dto.response.AuthenticationResponse;
import com.code_with_alfred.logistics_company.logistics.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(@Valid @RequestBody RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.ok(ApiResponse.success("User registered successfully", response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }
}