package com.hotspotted.server.dto;

import com.hotspotted.server.dto.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
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
    @Max(value = 2147483647, message = "The range can't be higher than 2147483647")
    @NotNull(message = "The range should be greater than 0")
    @Positive
    int range;

    @Valid
    @Schema(example = "FOOD")
    Category category;
}
