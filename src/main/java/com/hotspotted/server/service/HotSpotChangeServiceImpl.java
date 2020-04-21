package com.hotspotted.server.service;

import com.hotspotted.server.entity.HotSpotChange;
import com.hotspotted.server.repository.HotSpotChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public List<HotSpotChange> getAll() {
        return hotSpotChangeRepository.findAll();
    }

    @Override
    public Optional<HotSpotChange> findById(UUID id) {
        return hotSpotChangeRepository.findById(id);
    }
}
