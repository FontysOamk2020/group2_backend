package com.hotspotted.server.logic;

import com.hotspotted.server.entity.HotSpotChange;
import com.hotspotted.server.logic.factory.Operation.Operation;
import com.hotspotted.server.logic.factory.OperationFactory;
import com.hotspotted.server.service.HotSpotChangeService;
import com.hotspotted.server.service.HotSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class HotSpotChangeLogicImpl implements HotSpotChangeLogic {

    private final HotSpotChangeService hotSpotChangeService;
    private final HotSpotService hotSpotService;

    @Autowired
    public HotSpotChangeLogicImpl(HotSpotChangeService hotSpotChangeService, HotSpotService hotSpotService) {
        this.hotSpotChangeService = hotSpotChangeService;
        this.hotSpotService = hotSpotService;
    }

    @Override
    public HotSpotChange createOrUpdate(HotSpotChange change) {
        OperationFactory operationFactory = new OperationFactory();
        Operation operation = operationFactory.create(change, hotSpotService, hotSpotChangeService);
        return operation.execute();
//        return hotSpotChangeService.createOrUpdate(change);
    }

    @Override
    public List<HotSpotChange> getAll() {
        return hotSpotChangeService.getAll();
    }

    @Override
    public Optional<HotSpotChange> findById(UUID id) {
        return hotSpotChangeService.findById(id);
    }

}
