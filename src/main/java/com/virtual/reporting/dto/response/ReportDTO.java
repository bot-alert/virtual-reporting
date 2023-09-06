package com.virtual.reporting.dto.response;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private static final long serialVersionUID = -8354877764648325068L;

    @NotNull(message = "Report name is required")
    @Size(min = 1, max = 255, message = "Report name must be between 1 and 255 characters")
    private String reportName;

    @NotNull(message = "Report date is required")
    private Long reportDate;

    @NotNull(message = "Patient name is required")
    @Size(min = 1, max = 255, message = "Patient name must be between 1 and 255 characters")
    private String patientName;

    @Min(value = 0, message = "Patient age cannot be negative")
    private int patientAge;

    private boolean completed;
    private boolean underReview;

    @Size(max = 255, message = "File names list cannot exceed 255 characters")
    private List<String> files;

    @Lob
    @Size(max = 65535, message = "Report content is too large")
    private String reportContent;

    @NotNull(message = "Hospital ID is required")
    private Long hospitalId;

    @NotNull(message = "User ID is required")
    private Long userId;

}
