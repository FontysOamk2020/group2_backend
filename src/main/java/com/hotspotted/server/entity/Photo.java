package com.hotspotted.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "photo")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class Photo extends BaseEntity implements Serializable {
    @NotBlank
    private String imageUrl;
}