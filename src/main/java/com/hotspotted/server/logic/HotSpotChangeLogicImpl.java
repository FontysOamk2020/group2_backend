package com.hotspotted.server.logic;

import com.hotspotted.server.entity.HotSpotChange;
import com.hotspotted.server.service.HotSpotChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotSpotChangeLogicImpl implements HotSpotChangeLogic {

    private final HotSpotChangeService hotSpotChangeService;

    @Autowired
    public HotSpotChangeLogicImpl(HotSpotChangeService hotSpotChangeService) {
        this.hotSpotChangeService = hotSpotChangeService;
    }

    @Override
    public HotSpotChange createOrUpdate(HotSpotChange change) {
        return hotSpotChangeService.createOrUpdate(change);
    }

}
