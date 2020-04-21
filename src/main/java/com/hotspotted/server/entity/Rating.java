package com.hotspotted.server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hotspotted.server.entity.constant.ScoreValue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
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

    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JsonManagedReference
    private Student creator;

    @PrePersist
    public void updateStudentScore() {
        this.getCreator().setScore(this.creator.getScore() + ScoreValue.RATING);
    }
}

