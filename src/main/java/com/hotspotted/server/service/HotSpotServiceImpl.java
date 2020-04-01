package com.hotspotted.server.service;

import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.repository.HotSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotSpotServiceImpl implements HotSpotService {
    private final HotSpotRepository hotSpotRepository;

    @Autowired
    public HotSpotServiceImpl(HotSpotRepository hotSpotRepository) {
        this.hotSpotRepository = hotSpotRepository;
    }

    @Override
    public Optional<HotSpot> findBySlug(String slug) {
        return hotSpotRepository.findBySlug(slug);
    }

    @Override
    public List<HotSpot> findBySearchParams(HotSpotSearch search) {
        return hotSpotRepository.findBySearchParams(search.getLongitude(), search.getLatitude(), search.getRange(), search.getCategory());
    }

    @Override
    public HotSpot createOrUpdate(HotSpot hotspot) {
        return hotSpotRepository.save(hotspot);
    }
}
