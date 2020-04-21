package com.hotspotted.server.logic.factory;

import com.hotspotted.server.entity.HotSpotChange;
import com.hotspotted.server.service.HotSpotChangeService;
import com.hotspotted.server.service.HotSpotService;
import org.modelmapper.ModelMapper;

public abstract class OperationHelper {
    protected HotSpotService hotSpotService;
    protected HotSpotChangeService hotSpotChangeService;
    protected HotSpotChange hotSpotChange;
    protected final ModelMapper modelMapper = new ModelMapper();

    public OperationHelper(HotSpotChange hotSpotChange, HotSpotService hotSpotService, HotSpotChangeService hotSpotChangeService) {
        this.hotSpotService = hotSpotService;
        this.hotSpotChangeService = hotSpotChangeService;
        this.hotSpotChange = hotSpotChange;
    }
}
