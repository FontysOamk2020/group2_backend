package com.hotspotted.server.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class NewRating {

    @Min(value = 0, message = "rating can't be lower than 0")
    @Max(value = 5, message = "rating can't be higher than 5")
    @NotNull(message = "Please provide: rating")
    double rating;
}