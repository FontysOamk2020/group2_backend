package com.hotspotted.server.controller;

import com.hotspotted.server.controller.enums.Response;
import com.hotspotted.server.dto.NewHotSpot;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.HotSpotChange;
import com.hotspotted.server.entity.Student;
import com.hotspotted.server.entity.enums.RequestedChangeStatus;
import com.hotspotted.server.logic.HotSpotChangeLogic;
import com.hotspotted.server.logic.HotSpotLogic;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController()
@Tag(name = "HotSpotChanges", description = "All endpoints regarding hot spot changes")
@RequestMapping()
public class HotSpotChangeController {

    private final HotSpotChangeLogic hotSpotChangeLogic;
    private final HotSpotLogic hotSpotLogic;


    private static Logger logger = LoggerFactory.getLogger(HotSpotController.class);
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HotSpotChangeController(HotSpotChangeLogic hotSpotChangeLogic, HotSpotLogic hotSpotLogic) {
        this.hotSpotChangeLogic = hotSpotChangeLogic;
        this.hotSpotLogic = hotSpotLogic;
    }

    @PreAuthorize("hasAuthority('write:changerequest')")
    @PostMapping("/hotspot/{slug}/change")
    public ResponseEntity create(@PathVariable(value = "slug") String slug, @Valid @RequestBody NewHotSpot newHotSpot, @Parameter(hidden = true) @RequestAttribute("student") Student student) {
        HotSpot foundHotSpot = hotSpotLogic.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Response.NOT_FOUND.toString()));
        try {
            HotSpotChange hotSpotChangeFromDTO = modelMapper.map(newHotSpot, HotSpotChange.class);
            hotSpotChangeFromDTO.setCreator(student);
            hotSpotChangeFromDTO.setHotSpot(foundHotSpot);
            hotSpotChangeFromDTO.setStatus(RequestedChangeStatus.REQUESTED);
            hotSpotChangeLogic.createOrUpdate(hotSpotChangeFromDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Something went wrong", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }
    }

    @PreAuthorize("hasAuthority('approve:changerequest')")
    @PostMapping("/hotspotchange/{id}/{status}")
    public ResponseEntity update(@PathVariable(value = "id") UUID id, @PathVariable(value = "status") RequestedChangeStatus status) {
        HotSpotChange foundHotSpotChange = hotSpotChangeLogic.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Response.NOT_FOUND.toString()));
        try {
            foundHotSpotChange.setStatus(status);
            hotSpotChangeLogic.createOrUpdate(foundHotSpotChange);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Something went wrong", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }
    }

    @GetMapping("/hotspotchange")
    @PreAuthorize("hasAuthority('read:changerequest')")
    public List<HotSpotChange> getAll() {
        return hotSpotChangeLogic.getAll();
    }
}
