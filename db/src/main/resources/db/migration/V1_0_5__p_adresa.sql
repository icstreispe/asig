create table p_adresa (
    id integer primary key,

    localitate  national character varying (100),
    sublocalitate  national character varying (100),
    tara_id integer,
    judet_id  integer,
    strada  national character varying (100),
    numar  national character varying (100),
    bloc  national character varying (100),
    scara  national character varying (100),
    etaj  national character varying (100),
    apartament  national character varying (100),
    cod_postal  national character varying (100),
    tip_strada_id integer,      --TODO
    
    start_date date default now(),
    update_date date default now(),
    end_date date
);

CREATE SEQUENCE p_adresa_seq START 1;



