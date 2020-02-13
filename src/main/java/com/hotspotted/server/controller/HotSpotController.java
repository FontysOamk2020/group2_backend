package com.hotspotted.server.controller;

import com.hotspotted.server.controller.enums.Response;
import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.User;
import com.hotspotted.server.logic.HotSpotLogic;
import com.hotspotted.server.logic.UserLogic;
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
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController()
@RequestMapping("/hotspot")
public class HotSpotController {

    private final HotSpotLogic hotSpotLogic;

    private static Logger logger = LoggerFactory.getLogger(HotSpotController.class);

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
    public ResponseEntity create(@Valid @RequestBody HotSpot newHotSpot) {
        User user = new User();
        user.setName("TestMan");
        user.setEmail("test@test.com");

        try {
            HotSpot hotSpot = this.hotSpotLogic.createOrUpdate(newHotSpot);

            Map<Object, Object> model = new LinkedHashMap<>();
            model.put("hotspot", hotSpot);
            return ok(model);
        } catch (Exception e) {
            logger.error("Something went wrong", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }

    }
}
