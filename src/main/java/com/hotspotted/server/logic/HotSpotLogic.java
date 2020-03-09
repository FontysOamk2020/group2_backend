package com.hotspotted.server.logic;

import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.dto.enums.Category;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotSpotLogic {
    List<HotSpot> getBySearchParams(HotSpotSearch search);
    Optional<HotSpot> findBySlug(String slug);
    HotSpot createOrUpdate(HotSpot hotspot);
}
