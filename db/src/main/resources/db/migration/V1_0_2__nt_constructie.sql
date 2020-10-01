create table nt_constructie (
    id integer primary key,

    name national character varying (100),
    
    start_date date default now(),
    update_date date default now(),
    end_date date
);




