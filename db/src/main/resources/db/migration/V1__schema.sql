CREATE TABLE polite (
    id_polita bigint PRIMARY KEY,
    serie character varying (100),
    nr integer,
    --price decimal(10, 2),
    start_valid date,
    end_valid date,
    emis_la date,
    tip_plata integer
);
