package com.hotspotted.server.dto;

import com.hotspotted.server.dto.enums.Planet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
class Address {
    @NotNull(message = "Please provide: address.address")
    String address;

    @NotNull(message = "Please provide: address.postalCode")
    String postalCode;

    @NotNull(message = "Please provide: address.city")
    String city;

    @NotNull(message = "Please provide: address.country")
    String country;

    @Schema(example = "EARTH")
    @Valid
    Planet planet = Planet.EARTH;
}
