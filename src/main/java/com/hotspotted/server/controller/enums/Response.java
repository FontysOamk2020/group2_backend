package com.hotspotted.server.controller.enums;

public enum Response {
    INVALID_PARAMS("Invalid parameters");

    private final String text;

    Response(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}