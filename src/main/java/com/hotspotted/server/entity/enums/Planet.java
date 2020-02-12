package com.hotspotted.server.entity.enums;

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
