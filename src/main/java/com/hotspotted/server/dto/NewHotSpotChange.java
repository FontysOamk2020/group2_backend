package com.hotspotted.server.dto;

import com.hotspotted.server.entity.enums.RequestedChangeStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewHotSpotChange {

    RequestedChangeStatus status;

    @NotNull(message = "Please provide: hotspot")
    NewHotSpot newHotSpot;
}
