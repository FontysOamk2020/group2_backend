package com.hotspotted.server.dto.enums;

public enum Category {
    FOOD("Food"),
    SPORTS("Sports"),
    DRINKS("Drinks"),
    ARTS("Arts"),
    KNOWLEDGE("Knowledge"),
    MUSIC("Music");

    private final String text;

    Category(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}