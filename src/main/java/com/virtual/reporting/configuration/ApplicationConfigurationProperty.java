package com.virtual.reporting.configuration;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("app")
public record ApplicationConfigurationProperty(@NotBlank String superuser,
                                               @NotBlank String password,
@NotBlank  String jwtSecret) {
}
