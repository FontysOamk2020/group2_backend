package com.hotspotted.server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rating")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Rating extends BaseEntity implements Serializable {


    private double rating;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JsonManagedReference
    private Student creator;
}

