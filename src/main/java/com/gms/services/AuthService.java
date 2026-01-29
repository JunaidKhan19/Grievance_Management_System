package com.gms.services;

import com.gms.dto.auth.LoginRequestDTO;
import com.gms.dto.auth.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO request);
}
