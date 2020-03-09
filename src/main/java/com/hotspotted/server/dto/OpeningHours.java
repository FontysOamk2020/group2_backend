package com.hotspotted.server.dto;

import com.hotspotted.server.dto.enums.WeekDay;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
class OpeningHours {
    @Schema(example = "MONDAY")
    @NotNull(message = "Please provide: openingHours.weekDay")
    WeekDay weekDay;

    @Valid
    @Schema(example = "12:00:00")
    @NotNull(message = "Please provide: openingHours.openingTime")
    LocalTime openingTime;

    @Valid
    @Schema(example = "12:00:00")
    @NotNull(message = "Please provide: openingHours.closingTime")
    LocalTime closingTime;
}
