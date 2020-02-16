package com.hotspotted.server.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
class Location {
    @Min(value = -180, message = "longitude can't be lower than -180")
    @Max(value = 180, message = "longitude can't be higher than 180")
    @NotNull(message = "Please provide: location.longitude")
    double longitude;

    @Min(value = -90, message = "latitude can't be lower than -90")
    @Max(value = 90, message = "latitude can't be higher than 90")
    @NotNull(message = "Please provide: location.latitude")
    double latitude;
}