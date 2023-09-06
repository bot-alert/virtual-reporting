package com.virtual.reporting.util;

import com.virtual.reporting.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtEncoder jwtEncoder;
    public final JwtDecoder jwtDecoder;
    private static Set<String> blackListToken = new HashSet<>();
    private static final long EXPIRATION_TIME = 2 * 60 * 1000L;//Two is minute

    public String generateToken(User user) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(Instant.ofEpochMilli(System.currentTimeMillis()))
                .expiresAt(Instant.ofEpochMilli(System.currentTimeMillis() + EXPIRATION_TIME))
                .subject(user.getEmail())
                .claim("role", user.getRole())
                .build();

        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    public String[] getEmailAndRoleFromToken(String token) {
        if (blackListToken.contains(token)) {
            throw new JwtValidationException("Token Blacklisted", List.of(new OAuth2Error("black_listed", "Token blacklisted", null)));
        }

        Jwt jwt = jwtDecoder.decode(token);

        return new String[]{jwt.getSubject(), jwt.getClaimAsString("role")};
    }

    @Async
    public void removeExpiredToken(String token) {
        blackListToken.add(token);
        for (String tkn : blackListToken) {
            try {
                jwtDecoder.decode(tkn);
            } catch (JwtValidationException ignored) {
                blackListToken.remove(tkn);
            }
        }
    }
}
