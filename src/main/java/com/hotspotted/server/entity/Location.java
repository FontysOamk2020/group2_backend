package com.hotspotted.server.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "location")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class Location extends BaseEntity implements Serializable {
    @NotNull
    private float longitude;

    @NotNull
    private float latitude;
}