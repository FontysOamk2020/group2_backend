package com.hotspotted.server.logic;

import com.hotspotted.server.entity.*;
import com.hotspotted.server.service.HotSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class HotSpotLogicImpl implements HotSpotLogic {
    private final HotSpotService hotspotService;

    @Autowired
    public HotSpotLogicImpl(HotSpotService hotspotService) {
        this.hotspotService = hotspotService;
    }

    @Override
    public Optional<HotSpot> findById(UUID id) {
        return hotspotService.findById(id);
    }

    @Override
    public List<HotSpot> getByOwner(Student student) {
        return hotspotService.getByOwner(student);
    }

    @Override
    public void deleteById(UUID id) {
        hotspotService.deleteById(id);
    }

    @Override
    public HotSpot createOrUpdate(HotSpot hotspot) {
        return hotspotService.createOrUpdate(hotspot);
    }
}
