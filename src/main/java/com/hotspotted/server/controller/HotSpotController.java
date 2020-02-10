package com.hotspotted.server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("hotspot")
public class HotSpotController {

    @GetMapping("search")
    public String search() {
        return "Hello there";
    }

    @PreAuthorize("hasAuthority('write:hotspot')")
    @PostMapping()
    public String create() {
        return "Up and ready";
    }
}
