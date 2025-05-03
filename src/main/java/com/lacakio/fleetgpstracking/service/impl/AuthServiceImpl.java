package com.lacakio.fleetgpstracking.service.impl;

import com.lacakio.fleetgpstracking.dto.AuthResponse;
import com.lacakio.fleetgpstracking.dto.UserDTO;
import com.lacakio.fleetgpstracking.service.AuthService;
import com.lacakio.fleetgpstracking.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AuthServiceImpl implements AuthService {

    private static final String HARD_CODED_USERNAME = "admin";
    private static final String HARD_CODED_PASSWORD = "admin1234";

    @Value("${jwt.expired_time}")
    private long EXPIRATION_TIME;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponse login(UserDTO userDTO) {
        if (HARD_CODED_USERNAME.equals(userDTO.getUsername()) && HARD_CODED_PASSWORD.equals(userDTO.getPassword())) {
            String token = jwtUtil.generateToken(userDTO.getUsername());
            AuthResponse response = new AuthResponse();
            response.setToken(token);
            Date dateExp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
            response.setExpiredToken(String.valueOf(dateExp));
            return response;
        } else {
            return null;
        }
    }
}
