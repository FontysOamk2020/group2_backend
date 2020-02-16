package com.hotspotted.server.dto;

import com.hotspotted.server.dto.enums.Planet;
import lombok.Data;

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

    Planet planet = Planet.EARTH;
}
