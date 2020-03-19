CREATE TABLE "conferences"
(
    "conference_id"    serial       NOT NULL,
    "conference_name"  varchar(50)  NOT NULL,
    "html_description" TEXT         NOT NULL,
    "location"         varchar(100) NOT NULL,
    "date_start"       TIMESTAMP    NOT NULL,
    "date_end"         TIMESTAMP    NOT NULL,
    "owner"            integer      NOT NULL,
    "created_at"       TIMESTAMP    NOT NULL,
    "deleted"          bool         NOT NULL DEFAULT 'false',
    CONSTRAINT "conferences_pk" PRIMARY KEY ("conference_id"),
    CONSTRAINT "conferences_fk0" FOREIGN KEY ("owner") REFERENCES "users" ("user_id")
);

CREATE TABLE "events"
(
    "event_id"         serial       NOT NULL,
    "event_name"       varchar(50)  NOT NULL,
    "event_type"       integer      NOT NULL,
    "conference_id"    integer      NOT NULL,
    "html_description" TEXT         NOT NULL,
    "location"         varchar(100) NOT NULL,
    "time_start"       TIMESTAMP    NOT NULL,
    "time_end"         TIMESTAMP    NOT NULL,
    "created_by"       integer      NOT NULL,
    "deleted"          bool         NOT NULL DEFAULT 'false',
    CONSTRAINT "events_pk" PRIMARY KEY ("event_id"),
    CONSTRAINT "events_fk0" FOREIGN KEY ("event_type") REFERENCES "event_types" ("event_type_id"),
    CONSTRAINT "events_fk1" FOREIGN KEY ("conference_id") REFERENCES "conferences" ("conference_id"),
    CONSTRAINT "events_fk2" FOREIGN KEY ("created_by") REFERENCES "users" ("user_id")
);

CREATE TABLE "event_speakers"
(
    "user_id"    integer   NOT NULL,
    "event_id"   integer   NOT NULL,
    "created_by" integer   NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "deleted"    bool      NOT NULL DEFAULT 'false',
    CONSTRAINT "event_speakers_pk" PRIMARY KEY ("user_id", "event_id"),
    CONSTRAINT "event_speakers_fk0" FOREIGN KEY ("user_id") REFERENCES "users" ("user_id"),
    CONSTRAINT "event_speakers_fk1" FOREIGN KEY ("event_id") REFERENCES "events" ("event_id"),
    CONSTRAINT "event_speakers_fk2" FOREIGN KEY ("created_by") REFERENCES "users" ("user_id")
);