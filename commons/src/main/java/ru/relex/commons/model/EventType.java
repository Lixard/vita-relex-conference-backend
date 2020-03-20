package ru.relex.commons.model;

import java.util.Optional;

public enum EventType {

    MEETUP(1),
    LECTURE(2),
    SEMINAR(3),
    PRODUCT_PRESENTATION(4),
    ENTERTAINING_EVENT(5),
    ;

    private final int id;

    EventType(int id) {
        this.id = id;
    }

    public static Optional<EventType> of(final Integer id) {
        if (id == null) {
            return Optional.empty();
        }

        for (var value : EventType.values()) {
            if (value.id == id) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }

    public int getId() {
        return id;
    }
}
