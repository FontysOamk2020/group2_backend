package com.hotspotted.server.controller;

import com.hotspotted.server.logic.HotSpotChangeLogic;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Tag(name = "HotSpotChanges", description = "All endpoints regarding hot spot changes")
@RequestMapping("/hotspotchange")
public class HotSpotChangeController {

    private final HotSpotChangeLogic hotSpotChangeLogic;

    @Autowired
    public HotSpotChangeController(HotSpotChangeLogic hotSpotChangeLogic) {
        this.hotSpotChangeLogic = hotSpotChangeLogic;
    }


}
