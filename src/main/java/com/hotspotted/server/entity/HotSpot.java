package com.hotspotted.server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hotspotted.server.entity.enums.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
    private User creator;

    private Category category;

    @OneToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    private Address address;

    @OneToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    private Location location;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    private OpeningHours openingHours;
}
