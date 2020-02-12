package com.hotspotted.server.entity;

import com.hotspotted.server.entity.enums.WeekDay;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "opening-hours")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class OpeningHours extends BaseEntity implements Serializable {

    @OneToOne(
            fetch = FetchType.EAGER
    )
    private HotSpot hotSpot;

    @NotNull
    private WeekDay weekDay;
    private Date openingTime;
    private Date closingTime;
}