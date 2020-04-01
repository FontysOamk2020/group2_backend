package com.hotspotted.server.service;

import com.hotspotted.server.entity.HotSpotChange;

import java.util.List;

public interface HotSpotChangeService {
    HotSpotChange createOrUpdate(HotSpotChange change);
    List<HotSpotChange> findByHotSpotSlug(String slug);
}
