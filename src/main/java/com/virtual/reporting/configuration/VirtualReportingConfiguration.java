package com.virtual.reporting.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@RequiredArgsConstructor
public class VirtualReportingConfiguration {
    private final ApplicationConfigurationProperty appProperty;

    @Bean
    JwtEncoder jwtEncoder() {
        SecretKey key = new SecretKeySpec(appProperty.jwtSecret().getBytes(), "HmacSHA256");
        JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<>(key);
        return new NimbusJwtEncoder(immutableSecret);
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKey originalKey = new SecretKeySpec(appProperty.jwtSecret().getBytes(), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(originalKey).build();
    }
    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
