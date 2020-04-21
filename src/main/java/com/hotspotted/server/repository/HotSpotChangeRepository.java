package com.hotspotted.server.repository;

import com.hotspotted.server.entity.HotSpotChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotSpotChangeRepository extends JpaRepository<HotSpotChange, UUID> {
}
