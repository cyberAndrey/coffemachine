CREATE TABLE IF NOT EXISTS machine
(
    id     SERIAL PRIMARY KEY,
    name   VARCHAR(255),
    water  INT NOT NULL,
    beans  INT NOT NULL
);

CREATE TABLE IF NOT EXISTS coffee_type
(
    typeId    SERIAL PRIMARY KEY,
    name      VARCHAR(255),
    machine_id  INT NOT NULL
);

CREATE TABLE IF NOT EXISTS usage_log
(
    id    SERIAL PRIMARY KEY,
    timestamp  timestamp without time zone NOT NULL
        DEFAULT (current_timestamp AT TIME ZONE 'UTC'),
    coffee_type  INT NOT NULL
);

ALTER TABLE coffee_type
    ADD CONSTRAINT fk_coffee_type FOREIGN KEY(machine_id) REFERENCES machine(id);

ALTER TABLE usage_log
    ADD CONSTRAINT fk_coffee_type FOREIGN KEY(coffee_type) REFERENCES coffee_type(typeId);

INSERT INTO machine
(id, name, water, beans)
VALUES ( '1', 'Proxima F11 Plus', 2500, 800);

INSERT INTO coffee_type (typeId, name, machine_id)
VALUES ( '1', 'cappuccino', '1');

INSERT INTO coffee_type (typeId, name, machine_id)
VALUES ( '2', 'espresso', '1');

INSERT INTO coffee_type (typeId, name, machine_id)
VALUES ( '3', 'american', '1');