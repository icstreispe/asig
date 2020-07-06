create table p_imobil (
    id integer primary key,

    adresa_id integer,

    constructie_id integer,
    tip_mediu_id integer,
    tip_structura_id integer,

    suprafata double precision,
    an_constructie integer,
    nr_camere integer,
    nr_etaje integer,
    nr_cladiri integer,
    mentiuni national character varying (1000),
    vecinatate national character varying (1000),
    
    start_date date default now(),
    update_date date default now(),
    end_date date
);


create sequence p_imobil_seq start 1;



