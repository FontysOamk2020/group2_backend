package com.hotspotted.server.logic;

import com.github.slugify.Slugify;
import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.Student;
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
    public List<HotSpot> getBySearchParams(HotSpotSearch search) {
        return hotspotService.findBySearchParams(search.getLongitude(), search.getLatitude(), search.getRange());
    }

    @Override
    public List<HotSpot> findByName(String name) {
        return hotspotService.findByName(name);
    }

    @Override
    public void deleteById(UUID id) {
        hotspotService.deleteById(id);
    }

    @Override
    public HotSpot createOrUpdate(HotSpot hotspot) {
        Slugify slg = new Slugify();
        int count = hotspotService.findByName(hotspot.getName()).size() + 1;
        String slug = slg.slugify(hotspot.getName() + count);
        //hotspot.setSlug(slug)
        return hotspotService.createOrUpdate(hotspot);
    }
}
