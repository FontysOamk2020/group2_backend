package com.hotspotted.server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotspotted.server.dto.enums.Category;
import com.hotspotted.server.entity.constant.ScoreValue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
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

    @NotBlank
    @Column(unique = true)
    private String slug;

    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JsonManagedReference
    private Student creator;

    private Category category;

    @Override
    @JsonProperty
    public Date getCreatedAt() {
        return super.getCreatedAt();
    }

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private Address address;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private Location location;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private Set<OpeningHours> openingHours = new HashSet<>();

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<Rating> ratings = new HashSet<>();

    @Transient
    private double ratingAverage;

    @PreUpdate
    @PostLoad
    public void calculateRating() {
        if(getRatings().size() != 0) {
            this.ratingAverage = (getRatings().stream().mapToDouble(Rating::getRating).sum()) / getRatings().size();
        }
        else{
            this.ratingAverage = 0;
        }
    }

    @PrePersist
    public void updateStudentScore() {
        this.getCreator().setScore(this.creator.getScore() + ScoreValue.HOTSPOT);
    }
}
