create table n_perioada (
    id integer primary key,

    name national character varying (100),
    luni integer,
    
    start_date date default now(),
    update_date date default now(),
    end_date date
);




