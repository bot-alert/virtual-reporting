package com.virtual.reporting.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.reporting.dto.ApiResponseDTO;
import com.virtual.reporting.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (!StringUtils.hasText(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            filterChain.doFilter(request, response);
            return;
        }
        try {
            setAuthentication(request, response, filterChain, authHeader);
        } catch (JwtValidationException jwtValidationException) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(
                    ApiResponseDTO.failureBuilder()
                            .message(jwtValidationException.getErrors().stream().map(OAuth2Error::toString).toList())
                            .build())
            );
        }
    }

    private void setAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String authHeader) throws IOException, ServletException {
        String jwt = authHeader.substring(7);
        String[] userInfo = jwtUtil.getEmailAndRoleFromToken(jwt);

        var auth = new UsernamePasswordAuthenticationToken(userInfo[0], null, Collections.singletonList(new SimpleGrantedAuthority(userInfo[1])));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);
    }
}
