package com.lacakio.fleetgpstracking.service;

import com.lacakio.fleetgpstracking.dto.AuthResponse;
import com.lacakio.fleetgpstracking.dto.UserDTO;

public interface AuthService {

    public AuthResponse login(UserDTO userDTO);

}
