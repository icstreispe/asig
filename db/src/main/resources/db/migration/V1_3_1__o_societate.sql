create table o_asig (
    id integer primary key,

    name national character varying (100),
    adresa national character varying (200),
    reg_com national character varying (20),
    cui national character varying (200),
    telefon national character varying (20),
    fax national character varying (20),
    juridic integer,
    tip integer,

    start_date date default now(),
    update_date date default now(),
    end_date date
);


create sequence o_asig_seq start 1;



