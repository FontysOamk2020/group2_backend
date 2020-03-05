package com.hotspotted.server.logic;

import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.dto.enums.Category;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotSpotLogic {
    Optional<HotSpot> findById(UUID id);
    List<HotSpot> getBySearchParams(HotSpotSearch search);
    List<HotSpot> findByName(String name);
    void deleteById(UUID id);
    HotSpot createOrUpdate(HotSpot hotspot);
}
