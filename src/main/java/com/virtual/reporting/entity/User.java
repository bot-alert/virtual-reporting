package com.virtual.reporting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class User extends EntityAbstract implements Serializable {

    @Serial
    private static final long serialVersionUID = 1948044268623622492L;

    private String name;

    private String email;

    private String password;

    private Long dob;

    private String phoneNumber;
    @ManyToOne
    private Hospital hospital;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    @OneToMany
    private List<Report> reports = Collections.emptyList();
}



