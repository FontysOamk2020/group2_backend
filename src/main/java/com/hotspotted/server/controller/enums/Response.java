package com.hotspotted.server.controller.enums;

public enum Response {
    INVALID_PARAMS("Invalid parameters"),
    UNEXPECTED_ERROR("A unexpected error occurred. Try again later"),
    NOT_AUTHENTICATED("Authentication failed"),
    ACTION_NOT_ALLOWED("This action is not allowed");

    private final String text;

    Response(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}