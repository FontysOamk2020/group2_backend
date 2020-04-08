package com.hotspotted.server.controller;

import com.hotspotted.server.controller.enums.Response;
import com.hotspotted.server.dto.HotSpotSearch;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.HotSpotChange;
import com.hotspotted.server.logic.HotSpotChangeLogic;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController()
@Tag(name = "HotSpotChanges", description = "All endpoints regarding hot spot changes")
@RequestMapping("/hotspotchange")
public class HotSpotChangeController {

    private final HotSpotChangeLogic hotSpotChangeLogic;

    @Autowired
    public HotSpotChangeController(HotSpotChangeLogic hotSpotChangeLogic) {
        this.hotSpotChangeLogic = hotSpotChangeLogic;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('get:request')")
    public List<HotSpotChange> getAll() {
        return hotSpotChangeLogic.getAll();
    }
}
