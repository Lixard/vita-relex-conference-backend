package ru.relex.services.constraint;

public final class ConstraintMessage {
    private ConstraintMessage() {
    }

    public static class Field {
        private Field() {
        }

        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
        public static final String FIRST_NAME = "FIRSTNAME";
        public static final String LAST_NAME = "LASTNAME";
        public static final String ROLE = "ROLE";
        public static final String EMAIL = "EMAIL";
        public static final String PERSONAL_INFO = "PERSONALINFO";
    }

    public static class Constraint {
        private Constraint() {
        }

        public static final String IS_NULL = "_NULL";
        public static final String IS_EMPTY = "_EMPTY";
        public static final String TOO_LONG = "_TOOLONG";
        public static final String TOO_SHORT = "_TOOSHORT";
        public static final String NOT_UNIQUE = "_NOTUNIQUE";
        public static final String NOT_EMAIL ="_NOTEMAIL";
    }
}
