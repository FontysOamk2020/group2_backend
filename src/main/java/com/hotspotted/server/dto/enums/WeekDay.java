package com.hotspotted.server.dto.enums;

public enum WeekDay {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String text;

    WeekDay(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
