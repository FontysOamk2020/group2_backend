package com.hotspotted.server.dto.enums;

import lombok.Getter;

@Getter
public enum Category {
    FOOD("Food", "/markers/marker.svg"),
    SPORTS("Sports", "/markers/marker.svg"),
    DRINKS("Drinks", "/markers/marker.svg"),
    ARTS("Arts", "/markers/marker.svg"),
    KNOWLEDGE("Knowledge", "/markers/marker.svg"),
    MUSIC("Music", "/markers/marker.svg");

    private String text;
    private String markerPath;

    Category(String text, String markerPath) {
        this.text = text;
        this.markerPath = markerPath;
    }
}