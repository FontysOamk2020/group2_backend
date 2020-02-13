package com.hotspotted.server.repository;

import com.hotspotted.server.entity.HotSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface HotSpotRepository extends JpaRepository<HotSpot, UUID> {
}
