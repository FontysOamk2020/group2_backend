package com.hotspotted.server.controller;

import com.hotspotted.server.controller.enums.Response;
import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.dto.NewHotSpot;
import com.hotspotted.server.dto.enums.Category;
import com.hotspotted.server.entity.HotSpot;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.ResponseEntity.ok;

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
    @GetMapping("/search")
    public List<HotSpot> search(@Valid HotSpotSearch hotSpotSearch) {
        return hotSpotLogic.getBySearchParams(hotSpotSearch);
    }

    @SecurityRequirements
    @GetMapping("/{slug}")
    public HotSpot getHotSpot(@PathVariable(value = "slug") String slug) {
        return hotSpotLogic.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Response.NOT_FOUND.toString()));
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
            return this.hotSpotLogic.createOrUpdate(hotSpotFromDTO);
        } catch (Exception e) {
            logger.error("Something went wrong", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }
    }
}
