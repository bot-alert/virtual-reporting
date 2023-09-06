package com.virtual.reporting.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LoginDTO(@NotEmpty @Email String email,
                       @NotEmpty @Size(min = 8, max = 20) String password,
                       @Nullable Boolean rememberMe) {
}
