package com.hotspotted.server.logic.factory.Operation;

import com.hotspotted.server.entity.HotSpotChange;
import com.hotspotted.server.logic.factory.OperationHelper;
import com.hotspotted.server.service.HotSpotChangeService;
import com.hotspotted.server.service.HotSpotService;

public class DeniedOperation extends OperationHelper implements Operation {
    public DeniedOperation(HotSpotChange hotSpotChange, HotSpotService hotSpotService, HotSpotChangeService hotSpotChangeService) {
        super(hotSpotChange, hotSpotService, hotSpotChangeService);
    }

    @Override
    public HotSpotChange execute() {
        return hotSpotChangeService.createOrUpdate(hotSpotChange);
    }
}
