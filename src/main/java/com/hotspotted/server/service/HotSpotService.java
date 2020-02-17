package com.hotspotted.server.service;

import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotSpotService {
    Optional<HotSpot> findById(UUID id);

    List<HotSpot> findBySearchParams(double longitude, double latitude, int range);

    void deleteById(UUID id);

    HotSpot createOrUpdate(HotSpot hotspot);
}
