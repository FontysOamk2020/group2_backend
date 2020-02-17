package com.hotspotted.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotspotted.server.dto.enums.WeekDay;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "opening_hours")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class OpeningHours extends BaseEntity implements Serializable {
    @NotNull
    private WeekDay weekDay;

    @JsonFormat(pattern = "HH:mm")
    private Date openingTime;

    @JsonFormat(pattern = "HH:mm")
    private Date closingTime;
}