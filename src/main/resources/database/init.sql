create table if not exists users
(
    id         BIGINT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email      VARCHAR(255) UNIQUE NOT NULL,
    username   VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    gender     VARCHAR(10),
    balance    DOUBLE PRECISION DEFAULT 0,
    age        INT
);