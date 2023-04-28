CREATE TABLE car
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR NOT NULL,
    engine_id int     not null unique references engine (id)
);