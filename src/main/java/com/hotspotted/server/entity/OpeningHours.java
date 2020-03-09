package com.hotspotted.server.entity;

import com.hotspotted.server.dto.enums.WeekDay;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "opening_hours")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class OpeningHours extends BaseEntity implements Serializable {
    @NotNull
    private WeekDay weekDay;

    @NotNull
    @Schema(example = "12:00:00")
    @Column(columnDefinition = "TIME WITHOUT TIME ZONE")
    private LocalTime openingTime;

    @NotNull
    @Schema(example = "12:00:00")
    @Column(columnDefinition = "TIME WITHOUT TIME ZONE")
    private LocalTime closingTime;
}