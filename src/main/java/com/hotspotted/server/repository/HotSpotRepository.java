package com.hotspotted.server.repository;

import com.hotspotted.server.entity.HotSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotSpotRepository extends JpaRepository<HotSpot, UUID> {
    @Query(value = "SELECT * FROM hotspot AS H INNER JOIN location AS L on H.location_id = L.id WHERE earth_distance (ll_to_earth(L.latitude, L.longitude), ll_to_earth(:long, :lat)) <= :range", nativeQuery = true)
    List<HotSpot> findBySearchParams(@Param("long") double longitude, @Param("lat") double latitude, @Param("range") int range);

    @Query(value = "SELECT * FROM hotspot AS H WHERE H.name == :name", nativeQuery = true)
    List<HotSpot> findByName(@Param("name") String name);
}
