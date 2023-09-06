package com.virtual.reporting.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HospitalDTO {
    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "District is required")
    @Size(max = 100, message = "District cannot exceed 100 characters")
    private String district;

    @NotNull(message = "Province is required")
    @Size(max = 100, message = "Province cannot exceed 100 characters")
    private String province;

    @NotNull(message = "City is required")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String city;

    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be a 10-digit number")
    private String phoneNumber;
}