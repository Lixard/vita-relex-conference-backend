CREATE TABLE "event_types"
(
    "event_type_id" integer     NOT NULL,
    "name"          varchar(20) NOT NULL,
    CONSTRAINT "event_types_pk" PRIMARY KEY ("event_type_id")
);

CREATE TABLE "roles"
(
    "role_id" integer     NOT NULL,
    "name"    varchar(20) NOT NULL,
    CONSTRAINT "roles_pk" PRIMARY KEY ("role_id")
);

CREATE TABLE "users"
(
    "user_id"    serial      NOT NULL,
    "username"   varchar(50) NOT NULL UNIQUE,
    "first_name" varchar(50) NOT NULL,
    "last_name"  varchar(50) NOT NULL,
    "email"      varchar(50) NOT NULL,
    "password"   char(50)    NOT NULL,
    "role"       integer     NOT NULL,
    "deleted"    bool        NOT NULL DEFAULT 'false',
    CONSTRAINT "users_pk" PRIMARY KEY ("user_id"),
    CONSTRAINT "users_fk0" FOREIGN KEY ("role") REFERENCES "roles" ("role_id")
);
