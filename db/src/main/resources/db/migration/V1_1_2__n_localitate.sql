create table n_localitate (
       id serial primary key,
       name national character varying (100),
       apartenenta national character varying (100),
       population integer,
       firms integer,
       latitude double precision,
       longitude double precision,
       judet integer,


       start_date date default now(),
       update_date date default now(),
       end_date date
);
