create table n_judet (
    id integer primary key,
    name national character varying (100),
    cod national char (3),
    start_date date default now(),
    end_date date
);



