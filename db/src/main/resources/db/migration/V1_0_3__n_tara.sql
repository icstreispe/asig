create table n_tara (
    id integer primary key,
    name national character varying (100),
    name_en national character varying (100),
    population integer,
    area integer,
    start_date date default now(),
    end_date date
);



