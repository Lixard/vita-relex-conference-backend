CREATE TABLE "event_visitors"
(
    "user_id"  integer NOT NULL,
    "event_id" integer NOT NULL,
    "deleted"  bool    NOT NULL DEFAULT 'false',
    CONSTRAINT "event_visitors_pk" PRIMARY KEY ("user_id", "event_id"),
    CONSTRAINT "event_visitors_fk0" FOREIGN KEY ("user_id") REFERENCES "users" ("user_id"),
    CONSTRAINT "event_visitors_fk1" FOREIGN KEY ("event_id") REFERENCES "events" ("event_id")
);

CREATE TABLE "conference_organizers"
(
    "user_id"       integer   NOT NULL,
    "conference_id" integer   NOT NULL,
    "created_by"    integer   NOT NULL,
    "created_at"    TIMESTAMP NOT NULL,
    "deleted"       bool      NOT NULL DEFAULT 'false',
    CONSTRAINT "conference_organizers_pk" PRIMARY KEY ("user_id", "conference_id"),
    CONSTRAINT "conference_organizers_fk0" FOREIGN KEY ("user_id") REFERENCES "users" ("user_id"),
    CONSTRAINT "conference_organizers_fk1" FOREIGN KEY ("conference_id") REFERENCES "conferences" ("conference_id"),
    CONSTRAINT "conference_organizers_fk2" FOREIGN KEY ("created_by") REFERENCES "users" ("user_id")
);