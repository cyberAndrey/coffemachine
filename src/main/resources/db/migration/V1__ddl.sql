CREATE TABLE IF NOT EXISTS machine
(
    id     SERIAL PRIMARY KEY,
    name   VARCHAR(255),
    water  INT NOT NULL,
    beans  INT NOT NULL
);

CREATE TABLE IF NOT EXISTS coffee_type
(
    type_id    SERIAL PRIMARY KEY,
    name      VARCHAR(255),
    machine_id  INT NOT NULL
);

CREATE TABLE IF NOT EXISTS usage_log
(
    id    SERIAL PRIMARY KEY,
    timestamp  timestamp without time zone NOT NULL
        DEFAULT (current_timestamp AT TIME ZONE 'UTC'),
    action  VARCHAR(255) NOT NULL
);

ALTER TABLE coffee_type
    ADD CONSTRAINT fk_coffee_type FOREIGN KEY(machine_id) REFERENCES machine(id);

INSERT INTO machine
(id, name, water, beans)
VALUES ( '1', 'Proxima F11 Plus', 2500, 800);

INSERT INTO coffee_type (type_id, name, machine_id)
VALUES ( '1', 'cappuccino', '1');

INSERT INTO coffee_type (type_id, name, machine_id)
VALUES ( '2', 'espresso', '1');

INSERT INTO coffee_type (type_id, name, machine_id)
VALUES ( '3', 'american', '1');