package com.lacakio.fleetgpstracking.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String expiredToken;
}
