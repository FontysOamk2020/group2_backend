package com.hotspotted.server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Comment extends BaseEntity{

    @NotBlank
    private String text;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JsonManagedReference
    private Student user;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Photo photo;

    @Override
    @JsonProperty
    public Date getCreatedAt() {
        return super.getCreatedAt();
    }
}
