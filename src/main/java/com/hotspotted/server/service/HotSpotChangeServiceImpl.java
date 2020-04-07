package com.hotspotted.server.service;

import com.hotspotted.server.entity.HotSpotChange;
import com.hotspotted.server.repository.HotSpotChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotSpotChangeServiceImpl implements HotSpotChangeService {

    private final HotSpotChangeRepository hotSpotChangeRepository;

    @Autowired
    public HotSpotChangeServiceImpl(HotSpotChangeRepository hotSpotChangeRepository) {
        this.hotSpotChangeRepository = hotSpotChangeRepository;
    }

    @Override
    public HotSpotChange createOrUpdate(HotSpotChange change) {
        return hotSpotChangeRepository.save(change);
    }
}
