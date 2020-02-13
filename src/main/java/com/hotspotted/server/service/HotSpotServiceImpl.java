package com.hotspotted.server.service;

import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.User;
import com.hotspotted.server.repository.HotSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotSpotServiceImpl implements HotSpotService {

    private final HotSpotRepository hotSpotRepository;

    @Autowired
    public HotSpotServiceImpl(HotSpotRepository hotSpotRepository) {
        this.hotSpotRepository = hotSpotRepository;
    }

    @Override
    public Optional<HotSpot> findById(UUID id) {
        return hotSpotRepository.findById(id);
    }

    @Override
    public List<HotSpot> getByOwner(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(UUID id) {
        hotSpotRepository.deleteById(id);
    }

    @Override
    public HotSpot createOrUpdate(HotSpot hotspot) {
        throw new UnsupportedOperationException();
    }
}
