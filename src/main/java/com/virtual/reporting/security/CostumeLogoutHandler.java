package com.virtual.reporting.security;

import com.virtual.reporting.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CostumeLogoutHandler {
    private final JwtUtil jwtUtil;

    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        jwtUtil.removeExpiredToken(authHeader.substring(7));
    }
}
