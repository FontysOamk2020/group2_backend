package com.hotspotted.server.dto;

import com.hotspotted.server.dto.enums.WeekDay;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
class OpeningHours {
    @NotNull(message = "Please provide: openingHours.weekDay")
    WeekDay weekDay;

    @NotNull(message = "Please provide: openingHours.openingTime")
    LocalTime openingTime;

    @NotNull(message = "Please provide: openingHours.closingTime")
    LocalTime closingTime;
}
