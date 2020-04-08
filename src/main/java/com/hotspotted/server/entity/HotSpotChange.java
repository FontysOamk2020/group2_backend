package com.hotspotted.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotspotted.server.dto.enums.Category;
import com.hotspotted.server.entity.enums.RequestedChangeStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "hotspot_change")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class HotSpotChange extends BaseEntity implements Serializable {

    @Override
    @JsonProperty
    public UUID getId(){ return super.getId();}


    private RequestedChangeStatus status;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JsonManagedReference
    private Student creator;

    @Override
    @JsonProperty
    public Date getCreatedAt() {
        return super.getCreatedAt();
    }

    @NotBlank
    private String name;

    private String description;

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
