package com.hotspotted.server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hotspotted.server.dto.enums.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotspot")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class HotSpot extends BaseEntity implements Serializable {
    @NotBlank
    private String name;

    private String description;

    @ManyToOne(
        fetch = FetchType.EAGER,
        optional = false
    )
    @JsonManagedReference
    private Student creator;

    private Category category;

    @OneToOne(
            fetch = FetchType.EAGER,
            optional = false,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Address address;

    @OneToOne(
            fetch = FetchType.EAGER,
            optional = false,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Location location;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<OpeningHours> openingHours = new HashSet<>();
}
