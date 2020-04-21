package com.hotspotted.server.logic;

import com.hotspotted.server.entity.HotSpotChange;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotSpotChangeLogic {
    HotSpotChange createOrUpdate(HotSpotChange change);

    List<HotSpotChange> getAll();

    Optional<HotSpotChange> findById(UUID id);
}
