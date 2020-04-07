package com.hotspotted.server.logic;

import com.hotspotted.server.entity.HotSpotChange;

import java.util.List;

public interface HotSpotChangeLogic {
    HotSpotChange createOrUpdate(HotSpotChange change);
}
