package com.virtual.reporting.service;

import com.virtual.reporting.dto.ApiResponseDTO;
import com.virtual.reporting.dto.LoginDTO;
import com.virtual.reporting.dto.TokeDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ApiResponseDTO<TokeDTO> login(LoginDTO loginDTO);
}
