package com.hotspotted.server.controller;

import com.hotspotted.server.controller.enums.Response;
import com.hotspotted.server.dto.NewHotSpot;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.Student;
import com.hotspotted.server.logic.HotSpotLogic;
import io.swagger.v3.oas.annotations.Parameter;
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
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

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

    @GetMapping("/search")
    public String search() {
        return "Hello there";
    }

    @PreAuthorize("hasAuthority('write:hotspot')")
    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody NewHotSpot newHotSpot, @Parameter(hidden = true) @RequestAttribute("student") Student student) {
        try {
            HotSpot hotSpotFromDTO = modelMapper.map(newHotSpot, HotSpot.class);
            hotSpotFromDTO.setCreator(student);
            HotSpot hotSpot = this.hotSpotLogic.createOrUpdate(hotSpotFromDTO);

            Map<Object, Object> model = new LinkedHashMap<>();
            model.put("hotspot", hotSpot);
            return ok(model);
        } catch (Exception e) {
            logger.error("Something went wrong", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }

    }
}
