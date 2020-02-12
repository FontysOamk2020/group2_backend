package com.hotspotted.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class User extends BaseEntity implements Serializable {

    @OneToMany(
            fetch = FetchType.EAGER
    )
    private List<HotSpot> hotSpot;

    @NotBlank
    private String name;

    @NotBlank
    private String email;
}
