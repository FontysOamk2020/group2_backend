package com.hotspotted.server.service;

import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.Student;
import com.hotspotted.server.repository.AddressRepository;
import com.hotspotted.server.repository.HotSpotRepository;
import com.hotspotted.server.repository.LocationRepository;
import com.hotspotted.server.repository.OpeningHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotSpotServiceImpl implements HotSpotService {
    private final HotSpotRepository hotSpotRepository;
    private final AddressRepository addressRepository;
    private final LocationRepository locationRepository;
    private final OpeningHoursRepository openingHoursRepository;

    @Autowired
    public HotSpotServiceImpl(HotSpotRepository hotSpotRepository, AddressRepository addressRepository, LocationRepository locationRepository, OpeningHoursRepository openingHoursRepository) {
        this.hotSpotRepository = hotSpotRepository;
        this.addressRepository = addressRepository;
        this.locationRepository = locationRepository;
        this.openingHoursRepository = openingHoursRepository;
    }

    @Override
    public Optional<HotSpot> findById(UUID id) {
        return hotSpotRepository.findById(id);
    }

    @Override
    public List<HotSpot> getByOwner(Student student) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(UUID id) {
        hotSpotRepository.deleteById(id);
    }

    @Override
    public HotSpot createOrUpdate(HotSpot hotspot) {
//        addressRepository.save(hotspot.getAddress());
//        locationRepository.save(hotspot.getLocation());
//
//        hotspot.getOpeningHours().forEach(openingHoursRepository::save);

        return hotSpotRepository.save(hotspot);
    }
}
