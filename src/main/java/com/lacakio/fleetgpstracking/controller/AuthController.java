package com.lacakio.fleetgpstracking.controller;

import com.lacakio.fleetgpstracking.dto.UserDTO;
import com.lacakio.fleetgpstracking.dto.AuthResponse;
import com.lacakio.fleetgpstracking.service.AuthService;
import com.lacakio.fleetgpstracking.util.ApiResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        AuthResponse response = authService.login(userDTO);

        if (response != null) {
            return ApiResponseUtil.successResponse("Login successful", response);
        }

        return ApiResponseUtil.unauthorizedResponse("username or password wrong", request);
    }
}