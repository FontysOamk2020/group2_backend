package com.hotspotted.server.service;

import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.dto.enums.Category;
import com.hotspotted.server.entity.HotSpot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotSpotService {
    List<HotSpot> findBySearchParams(HotSpotSearch search);
    Optional<HotSpot> findBySlug(String slug);
    HotSpot createOrUpdate(HotSpot hotspot);
}
