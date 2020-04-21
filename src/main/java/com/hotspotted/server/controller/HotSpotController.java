package com.hotspotted.server.controller;

import com.hotspotted.server.controller.enums.Response;
import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.dto.NewHotSpot;
import com.hotspotted.server.dto.NewRating;
import com.hotspotted.server.dto.enums.Category;
import com.hotspotted.server.entity.Comment;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.Rating;
import com.hotspotted.server.entity.Student;
import com.hotspotted.server.logic.HotSpotLogic;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController()
@Tag(name = "HotSpots", description = "All endpoints regarding hot spots")
@RequestMapping("/hotspot")
public class HotSpotController {
    private final HotSpotLogic hotSpotLogic;

    private static Logger logger = LoggerFactory.getLogger(HotSpotController.class);
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HotSpotController(HotSpotLogic hotSpotLogic) {
        this.hotSpotLogic = hotSpotLogic;
    }

    @SecurityRequirements
    @GetMapping("/{slug}")
    public HotSpot getHotSpot(@PathVariable(value = "slug") String slug) {
        return hotSpotLogic.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Response.NOT_FOUND.toString()));
    }

    @SecurityRequirements
    @GetMapping("/search")
    public List<HotSpot> search(@Valid HotSpotSearch hotSpotSearch) {
        return hotSpotLogic.getBySearchParams(hotSpotSearch);
    }

    @SecurityRequirements
    @ApiResponse(responseCode = "200", description = "Categories retrieved successfully", content = @Content(schema = @Schema(example = "[{ value: 'value', name: 'name', markerPath: 'markerPath' }]")))
    @GetMapping("/categories")
    public List<Map<String, String>> getCategories() {
            return Stream.of(Category.values()).parallel().map(category -> {
                Map<String, String> obj = new HashMap<>();
                obj.put("value", category.toString());
                obj.put("name", category.getText());
                obj.put("markerPath", category.getMarkerPath());
                return obj;
            }).collect(Collectors.toList());
        }

    @PreAuthorize("hasAuthority('write:hotspot')")
    @PostMapping()
    public HotSpot create(@Valid @RequestBody NewHotSpot newHotSpot, @Parameter(hidden = true) @RequestAttribute("student") Student student) {
        try {
            HotSpot hotSpotFromDTO = modelMapper.map(newHotSpot, HotSpot.class);
            hotSpotFromDTO.setCreator(student);
            return this.hotSpotLogic.create(hotSpotFromDTO);
        } catch (Exception e) {
            logger.error("Something went wrong", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }
    }

    @PostMapping(value = "/{slug}/comment", consumes = {"multipart/form-data"})
    public HotSpot addComment(@RequestParam("text") @NotBlank String text, @RequestParam(value = "photo", required = false) MultipartFile photo, @PathVariable(value = "slug") String slug, @Parameter(hidden = true) @RequestAttribute("student") Student student)
    {
        HotSpot hotspot = hotSpotLogic.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Response.NOT_FOUND.toString()));

        try {
            Comment comment = new Comment();
            comment.setText(text);
            comment.setUser(student);
            return this.hotSpotLogic.addComment(hotspot, comment, photo);
        } catch (Exception e) {
            logger.error("Something went wrong", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }
    }

    @PostMapping("/{slug}/rate")
    public HotSpot addRating(@Valid @RequestBody NewRating newRating, @PathVariable(value = "slug") String slug, @Parameter(hidden = true) @RequestAttribute("student") Student student)
    {
        HotSpot hotSpot = hotSpotLogic.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Response.NOT_FOUND.toString()));

        try {
            Rating ratingFromDTO = modelMapper.map(newRating, Rating.class);
            ratingFromDTO.setCreator(student);
            return this.hotSpotLogic.addRating(hotSpot, ratingFromDTO);
        }
        catch (Exception e) {
            logger.error("Something went wrong", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }
    }
}
