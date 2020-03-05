package com.hotspotted.server.logic;

import com.github.slugify.Slugify;
import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.service.HotSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Optional;

@Component
public class HotSpotLogicImpl implements HotSpotLogic {
    private final HotSpotService hotspotService;
    private final Slugify slugHelper = new Slugify();

    @Autowired
    public HotSpotLogicImpl(HotSpotService hotspotService) {
        this.hotspotService = hotspotService;
    }

    @Override
    public List<HotSpot> getBySearchParams(HotSpotSearch search) {
        return hotspotService.findBySearchParams(search);
    }

    @Override
    public Optional<HotSpot> findBySlug(String slug) {
        return hotspotService.findBySlug(slug);
    }

    @Override
    public HotSpot createOrUpdate(HotSpot hotspot) {
        hotspot.setSlug(generateSlug(hotspot.getName()));
        return hotspotService.createOrUpdate(hotspot);
    }

    private String generateSlug(String name) {
        String baseSlug = slugHelper.slugify(name);

        String slug = baseSlug;
        while(this.hotspotService.findBySlug(slug).isPresent()) {
            slug = baseSlug + "-" + RandomStringUtils.randomAlphabetic(5).toLowerCase();
        }

        return slug;
    }
}
