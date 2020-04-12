DROP TABLE album;
CREATE TABLE "album"
(
    "photo_id"      serial   NOT NULL,
    "conference_id" integer NOT NULL,
    "url"           varchar(250) NOT NULL,
    "description"   varchar(300),
    "created_at"     TIMESTAMP,
    CONSTRAINT "conference_album_fk1" FOREIGN KEY ("conference_id") REFERENCES "conferences" ("conference_id")
);
