package com.hotspotted.server.controller;

import com.hotspotted.server.controller.enums.Response;
import com.hotspotted.server.dto.NewHotSpotChange;
import com.hotspotted.server.entity.HotSpotChange;
import com.hotspotted.server.entity.Student;
import com.hotspotted.server.logic.HotSpotChangeLogic;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "HotSpotChanges", description = "All endpoints regarding hot spot changes")
@RequestMapping("/hotspotchange")
public class HotSpotChangeController {

    private final HotSpotChangeLogic hotSpotChangeLogic;

    private static Logger logger = LoggerFactory.getLogger(HotSpotController.class);
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HotSpotChangeController(HotSpotChangeLogic hotSpotChangeLogic) {
        this.hotSpotChangeLogic = hotSpotChangeLogic;
    }

    @PostMapping()
    public HotSpotChange create(@Valid @RequestBody NewHotSpotChange newHotSpotChange, @Parameter(hidden = true) @RequestAttribute("student") Student student) {
        try {
            HotSpotChange hotSpotChangeFromDTO = modelMapper.map(newHotSpotChange.getNewHotSpot(), HotSpotChange.class);
            hotSpotChangeFromDTO.setCreator(student);
            return hotSpotChangeFromDTO;
//            return this.hotSpotChangeLogic.createOrUpdate(hotSpotChangeFromDTO);
        } catch (Exception e) {
            logger.error("Something went wrong", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('get:request')")
    public List<HotSpotChange> getAll() {
        return hotSpotChangeLogic.getAll();
    }
}
