CREATE TABLE owners
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL,
    user_id int     not null references auto_user (id)
);