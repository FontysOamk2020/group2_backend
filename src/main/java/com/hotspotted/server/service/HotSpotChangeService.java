package com.hotspotted.server.service;

import com.hotspotted.server.entity.HotSpotChange;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotSpotChangeService {
    HotSpotChange createOrUpdate(HotSpotChange change);

    List<HotSpotChange> getAll();

    Optional<HotSpotChange> findById(UUID id);
}
