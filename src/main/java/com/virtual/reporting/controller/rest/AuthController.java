package com.virtual.reporting.controller.rest;

import com.virtual.reporting.dto.ApiResponseDTO;
import com.virtual.reporting.dto.LoginDTO;
import com.virtual.reporting.dto.TokeDTO;
import com.virtual.reporting.security.CostumeLogoutHandler;
import com.virtual.reporting.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final CostumeLogoutHandler logoutHandler;

    @PostMapping("/login")
    public ApiResponseDTO<TokeDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @GetMapping("/logout")
    public ApiResponseDTO<String> logout(HttpServletRequest httpServletRequest) {
        logoutHandler.logout(httpServletRequest);
        return ApiResponseDTO.<String>successBuilderNoData()
                        .message("Successfully logged out.")
                        .build();
    }

    @GetMapping
    public String hi() {
        return "HELLO";
    }
}
