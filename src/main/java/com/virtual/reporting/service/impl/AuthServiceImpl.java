package com.virtual.reporting.service.impl;

import com.virtual.reporting.dto.ApiResponseDTO;
import com.virtual.reporting.dto.LoginDTO;
import com.virtual.reporting.dto.TokeDTO;
import com.virtual.reporting.dto.helper.MyPrinciple;
import com.virtual.reporting.service.AuthService;
import com.virtual.reporting.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public ApiResponseDTO<TokeDTO> login(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            MyPrinciple principle = (MyPrinciple) authentication.getPrincipal();
            String accessToken = jwtUtil.generateToken(principle.getUser());
            return ApiResponseDTO.successBuilder(new TokeDTO(accessToken, "Bearer "))
                    .message("Jwt build successfully.")
                    .build();
        } catch (Exception e) {
            return ApiResponseDTO.<TokeDTO>failureBuilder()
                    .message(e.getMessage())
                    .build();
        }
    }
}
