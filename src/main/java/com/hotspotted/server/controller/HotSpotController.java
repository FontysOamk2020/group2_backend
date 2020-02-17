package com.hotspotted.server.controller;

import com.hotspotted.server.controller.enums.Response;
import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.dto.NewHotSpot;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.Student;
import com.hotspotted.server.logic.HotSpotLogic;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController()
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
