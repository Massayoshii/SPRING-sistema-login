package com.example.login.controller;

import com.example.login.dto.request.UserLoginRequest;
import com.example.login.dto.request.UserRegisterRequest;
import com.example.login.dto.response.UserLoginResponse;
import com.example.login.dto.response.UserRegisterResponse;
import com.example.login.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody @Valid UserRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.regiter(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody @Valid UserLoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}
