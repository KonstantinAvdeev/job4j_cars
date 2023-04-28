CREATE TABLE history_owners
(
    id       SERIAL PRIMARY KEY,
    car_id   int not null references car (id),
    owner_id int not null references owners (id)
);