CREATE TABLE history
(
    id       SERIAL PRIMARY KEY,
    startAt  TIMESTAMP NOT NULL,
    endAt    TIMESTAMP NOT NULL,
    owner_id int       not null references owners (id)
);