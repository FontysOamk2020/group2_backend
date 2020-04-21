package com.hotspotted.server.logic;

import com.github.slugify.Slugify;
import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.entity.Comment;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.Photo;
import com.hotspotted.server.entity.Rating;
import com.hotspotted.server.exception.NotAllowedException;
import com.hotspotted.server.service.HotSpotService;
import com.hotspotted.server.service.ImageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Component
public class HotSpotLogicImpl implements HotSpotLogic {
    private final HotSpotService hotspotService;
    private final ImageService imageService;

    private final Slugify slugHelper = new Slugify();

    @Autowired
    public HotSpotLogicImpl(HotSpotService hotspotService, ImageService imageService) {
        this.hotspotService = hotspotService;
        this.imageService = imageService;
    }

    @Override
    public List<HotSpot> getBySearchParams(HotSpotSearch search) {
        return hotspotService.findBySearchParams(search);
    }

    @Override
    public Optional<HotSpot> findBySlug(String slug) {
        return hotspotService.findBySlug(slug);
    }

    @Override
    public HotSpot createOrUpdate(HotSpot hotSpot) {
        return hotspotService.createOrUpdate(hotSpot);
    }

    @Override
    public HotSpot updateRating(HotSpot hotSpot, Rating newRating) throws NotAllowedException {
        Optional<Rating> foundRating = hotSpot.getRatings().stream().filter(rating -> rating.getCreator().getId().equals(newRating.getCreator().getId())).findFirst();

        if(foundRating.isPresent()) {
            hotSpot.getRatings().removeIf(rating -> rating.getId().equals(foundRating.get().getId()));
            hotSpot.getRatings().add(newRating);
            HotSpot updatedHotSpot = hotspotService.createOrUpdate(hotSpot);
            updatedHotSpot.calculateRating();
            return updatedHotSpot;
        } else {
            throw new NotAllowedException("Can not update a rating that is not there");
        }
    }

    @Override
    public HotSpot create(HotSpot hotSpot) {
        hotSpot.setSlug(generateSlug(hotSpot.getName()));
        return hotspotService.createOrUpdate(hotSpot);
    }

    @Override
    public HotSpot addRating(HotSpot hotSpot, Rating newRating) throws NotAllowedException {
            if (hotSpot.getRatings().stream().anyMatch(rating -> rating.getCreator().getId().equals(newRating.getCreator().getId()))) {
                return updateRating(hotSpot, newRating);
            }
            else {
                hotSpot.getRatings().add(newRating);
                return createOrUpdate(hotSpot);
            }
    }

    @Override
    public HotSpot addComment(HotSpot hotSpot, Comment newComment, MultipartFile image) {
        if(image != null && !image.isEmpty()) {
            Photo photo = new Photo();
            photo.setImageUrl(imageService.upload(image));
            newComment.setPhoto(photo);
        }

        hotSpot.getComments().add(newComment);

        return createOrUpdate(hotSpot);
    }

    private String generateSlug(String name) {
        String baseSlug = slugHelper.slugify(name);

        String slug = baseSlug;
        while(this.hotspotService.findBySlug(slug).isPresent()) {
            slug = baseSlug + "-" + RandomStringUtils.randomAlphabetic(5).toLowerCase();
        }

        return slug;
    }
}
