package com.hotspotted.server.logic.factory;

import com.hotspotted.server.entity.HotSpotChange;
import com.hotspotted.server.logic.factory.Operation.AcceptedOperation;
import com.hotspotted.server.logic.factory.Operation.DeniedOperation;
import com.hotspotted.server.logic.factory.Operation.Operation;
import com.hotspotted.server.logic.factory.Operation.RequestedOperation;
import com.hotspotted.server.service.HotSpotChangeService;
import com.hotspotted.server.service.HotSpotService;

public class OperationFactory {

    public Operation create(HotSpotChange hotSpotChange, HotSpotService hotSpotService, HotSpotChangeService hotSpotChangeService) {
        switch (hotSpotChange.getStatus()) {
            case REQUESTED:
                return new RequestedOperation(hotSpotChange, hotSpotService, hotSpotChangeService);
            case ACCEPTED:
                return new AcceptedOperation(hotSpotChange, hotSpotService, hotSpotChangeService);
            case DENIED:
                return new DeniedOperation(hotSpotChange, hotSpotService, hotSpotChangeService);
        }
        return null;
    }
}
