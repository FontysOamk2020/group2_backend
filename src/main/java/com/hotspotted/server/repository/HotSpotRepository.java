package com.hotspotted.server.repository;

import com.hotspotted.server.dto.enums.Category;
import com.hotspotted.server.entity.HotSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HotSpotRepository extends JpaRepository<HotSpot, UUID> {
    @Query("FROM HotSpot H INNER JOIN H.location L WHERE FUNCTION('earth_distance', FUNCTION('ll_to_earth', L.latitude, L.longitude), FUNCTION('ll_to_earth', :long, :lat)) <= :range and " + "(:cat is null or H.category = :cat)")
    List<HotSpot> findBySearchParams(@Param("long") double longitude, @Param("lat") double latitude, @Param("range") int range, @Param("cat") Optional<Category> category);
}
