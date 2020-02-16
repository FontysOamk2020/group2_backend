package com.hotspotted.server.dto.enums;

public enum Planet {
    EARTH("Earth");

    private final String text;

    Planet(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
