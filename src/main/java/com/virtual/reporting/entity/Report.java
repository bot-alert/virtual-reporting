package com.virtual.reporting.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Report extends EntityAbstract implements Serializable {
    @Serial
    private static final long serialVersionUID = -8354877764648325068L;

    private String reportName;
    private Long reportDate;
    private String patientName;
    private int patientAge;
    private boolean completed;
    private boolean underReview;
    @ElementCollection
    private List<String> files = new ArrayList<>();
    @Lob
    private String reportContent;

    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private User user;
}
