package com.hotspotted.server.service;

import com.hotspotted.server.dto.enums.Category;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.Student;
import com.hotspotted.server.repository.HotSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotSpotServiceImpl implements HotSpotService {
    private final HotSpotRepository hotSpotRepository;

    @Autowired
    public HotSpotServiceImpl(HotSpotRepository hotSpotRepository) {
        this.hotSpotRepository = hotSpotRepository;
    }

    @Override
    public Optional<HotSpot> findById(UUID id) {
        return hotSpotRepository.findById(id);
    }

    @Override
    public List<HotSpot> findBySearchParams(double longitude, double latitude, int range, Optional<Category> category) {
        return hotSpotRepository.findBySearchParams(longitude, latitude, range, category);
    }

    @Override
    public List<HotSpot> findByName(String name) {
        return hotSpotRepository.findByName(name);
    }

    @Override
    public void deleteById(UUID id) {
        hotSpotRepository.deleteById(id);
    }

    @Override
    public HotSpot createOrUpdate(HotSpot hotspot) {
        return hotSpotRepository.save(hotspot);
    }
}
