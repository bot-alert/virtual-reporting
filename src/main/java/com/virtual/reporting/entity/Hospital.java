package com.virtual.reporting.entity;

import jakarta.persistence.Entity;
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
public class Hospital extends EntityAbstract implements Serializable {
    @Serial
    private static final long serialVersionUID = 1691816226231946436L;
    @OneToMany(mappedBy = "hospital")
    private List<User> users = Collections.emptyList();
    private String district;
    private String province;
    private String city;
    private String phoneNumber;
    @OneToMany(mappedBy = "hospital")
    private List<Report> reports = Collections.emptyList();
}
