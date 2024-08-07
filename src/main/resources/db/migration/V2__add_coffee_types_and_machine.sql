INSERT INTO machine
(id, name, water, beans)
VALUES ( '2', 'Saeco Aulika EVO Top', 3000, 700);

ALTER TABLE coffee_type
    ADD volume INT NOT NULL DEFAULT 0;

ALTER TABLE coffee_type
    ADD substance INT NOT NULL DEFAULT 0;

INSERT INTO coffee_type (type_id, name, machine_id, volume, substance)
VALUES ( '4', 'latte', '2', 300, 12);

INSERT INTO coffee_type (type_id, name, machine_id, volume, substance)
VALUES ( '5', 'raf', '2', 400, 10);

update coffee_type set volume=250, substance=15 where type_id=1;
update coffee_type set volume=150, substance=25 where type_id=2;
update coffee_type set volume=350, substance=20 where type_id=3;

CREATE TABLE IF NOT EXISTS machine_info
(
    id    SERIAL PRIMARY KEY,
    water  INT NOT NULL,
    beans  INT NOT NULL
);

INSERT INTO machine_info (id, water, beans)
VALUES ( '1', 2500, 800);

INSERT INTO machine_info (id, water, beans)
VALUES ( '2', 3000, 700);