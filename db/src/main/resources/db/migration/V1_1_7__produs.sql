create table produs (
    id integer primary key,

    name national character varying (100),
    idsoc integer,
    tip_asig integer,

    start_date date default now(),
    update_date date default now(),
    end_date date
);






