package com.hotspotted.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class HotSpotSearch {
    @Min(value = -180, message = "longitude can't be lower than -180")
    @Max(value = 180, message = "longitude can't be higher than 180")
    @NotNull(message = "Please provide: longitude")
    double longitude;

    @Min(value = -90, message = "latitude can't be lower than -90")
    @Max(value = 90, message = "latitude can't be higher than 90")
    @NotNull(message = "Please provide: latitude")
    double latitude;

    @Schema(description = "The range (in meters)", required = true, example = "50")
    @NotNull
    @Positive
    int range;
}
