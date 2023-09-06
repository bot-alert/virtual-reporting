package com.virtual.reporting.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Role extends EntityAbstract implements Serializable {

    @Serial
    private static final long serialVersionUID = -6407345863102108246L;
    private String name;
}
