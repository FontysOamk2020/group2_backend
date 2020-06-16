package com.hotspotted.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames="sub"))
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class Student extends BaseEntity implements Serializable {
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.EAGER,
            mappedBy = "creator",
            orphanRemoval = true
    )
    @JsonBackReference
    private Set<HotSpot> hotSpots = new HashSet<>();

    @NotBlank
    private String nickname;

    @NotBlank
    @Column(unique = true)
    private String sub;

    @NotBlank
    private String name;

    @JsonIgnore
    @NotBlank
    private String email;

    @NotBlank
    private String picture;

    @Column(columnDefinition = "integer default 0")
    private int score = 0;
}