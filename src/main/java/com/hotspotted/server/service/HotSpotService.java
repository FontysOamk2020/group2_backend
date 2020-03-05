package com.hotspotted.server.service;

import com.hotspotted.server.dto.enums.Category;
import com.hotspotted.server.entity.HotSpot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotSpotService {
    Optional<HotSpot> findById(UUID id);

    List<HotSpot> findBySearchParams(double longitude, double latitude, int range, Optional<Category> category);

    List<HotSpot> findByName(String name);

    void deleteById(UUID id);

    HotSpot createOrUpdate(HotSpot hotspot);
}
