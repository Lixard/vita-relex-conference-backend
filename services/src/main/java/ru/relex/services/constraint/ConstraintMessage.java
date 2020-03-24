package ru.relex.services.constraint;

public final class ConstraintMessage {
    private ConstraintMessage() {
    }

    public static class Field {
        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
        public static final String FIRST_NAME = "FIRSTNAME";
        public static final String LAST_NAME = "LASTNAME";
        public static final String ROLE = "ROLE";
        public static final String EMAIL = "EMAIL";
        public static final String PERSONAL_INFO = "PERSONALINFO";
        public static final String USER_ID = "USERID";
        public static final String EVENT_ID = "EVENTID";
        public static final String CONFERENCE_ID = "CONFERENCEID";
        public static final String EVENT_NAME = "EVENTNAME";
        public static final String CONFERENCE_NAME = "CONFERENCENAME";
        public static final String EVENT_TYPE = "EVENTTYPE";
        public static final String DETAILS = "DETAILS";
        public static final String HTML_DESCRIPTION = "HTMLDESCRIPION";
        public static final String LOCATION = "LOCATION";
        public static final String TIME_START = "TIMESTART";
        public static final String TIME_END = "TIMEEND";
        public static final String DATE_START = "DATESTART";
        public static final String DATE_END = "DATEEND";
        private Field() {
        }
    }

    public static class Constraint {
        public static final String IS_NULL = "_NULL";
        public static final String IS_EMPTY = "_EMPTY";
        public static final String TOO_LONG = "_TOOLONG";
        public static final String TOO_SHORT = "_TOOSHORT";
        public static final String NOT_UNIQUE = "_NOTUNIQUE";
        public static final String NOT_EMAIL = "_NOTEMAIL";
        public static final String NOT_EXISTS = "_NOTEXISTS";
        private Constraint() {
        }
    }
}
