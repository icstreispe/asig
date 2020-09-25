create table o_unitate (
    id integer primary key,

    name national character varying (100),
    adresa national character varying (200),
    telefon national character varying (20),
    fax national character varying (20),
    idsoc integer,

    start_date date default now(),
    update_date date default now(),
    end_date date
);


create sequence o_unitate_seq start 1;



