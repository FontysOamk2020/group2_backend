package com.hotspotted.server.dto;

import com.hotspotted.server.dto.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class NewHotSpot {
    @NotEmpty(message = "Please provide: name")
    String name;

    String description;

    @Valid
    @NotNull(message = "Please provide: address")
    Address address;

    @Schema(example = "FOOD")
    @Valid
    @NotNull(message = "Please provide: category")
    Category category;

    @Valid
    @NotNull(message = "Please provide: location")
    Location location;

    @Valid
    List<OpeningHours> openingHours;
}
