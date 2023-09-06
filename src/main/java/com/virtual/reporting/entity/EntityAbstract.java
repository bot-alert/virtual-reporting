package com.virtual.reporting.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
abstract class EntityAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private Long createdAt;
    @LastModifiedDate
    private Long updatedAt;

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) this.createdAt = new Date().getTime();
        if (this.updatedAt == null) this.updatedAt = new Date().getTime();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date().getTime();
    }

}
