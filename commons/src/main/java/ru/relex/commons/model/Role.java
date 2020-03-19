package ru.relex.commons.model;

import java.util.Optional;

public enum Role {

    ADMIN(1),
    USER(2),
    COMPANY_ACCOUNT(3),
    ;

    private final int id;

    Role(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Optional<Role> of(final Integer id) {
        if (id == null) {
            return Optional.empty();
        }

        for (var value: Role.values()) {
            if (value.id == id) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }
}
