package org.launchcode.codingevents.models;

public enum EventType {
    //enum special type of java class

    CONFERENCE("Conference"),
    MEETUP("Meetup"),
    WORKSHOP("Workshop"),
    SOCIAL("Social");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    //no setter(displayName is final)
    public String getDisplayName() {
        return displayName;
    }
}
