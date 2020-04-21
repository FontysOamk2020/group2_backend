package com.hotspotted.server.logic;

import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.entity.Comment;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.Rating;
import com.hotspotted.server.exception.NotAllowedException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface HotSpotLogic {
    List<HotSpot> getBySearchParams(HotSpotSearch search);
    Optional<HotSpot> findBySlug(String slug);
    HotSpot createOrUpdate(HotSpot hotSpot);
    HotSpot create(HotSpot hotSpot);
    HotSpot addComment(HotSpot hotSpot, Comment comment, MultipartFile image);
    HotSpot addRating(HotSpot hotSpot, Rating rating) throws NotAllowedException;
    HotSpot updateRating(HotSpot hotSpot, Rating rating) throws NotAllowedException;
}
