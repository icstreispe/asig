create table p_persoana (
    id integer primary key,

    cnp national character varying (15),
    nume national character varying (100),
    prenume national character varying (100),

    an_permis integer,
    ci_serie national character varying (20),
    ci_numar national character varying (20),
    adresa_id integer,
    cetatenie_id integer,
    nationalitate_id integer,

    telefon national character varying (20),
    fax national character varying (20),
    email national character varying (100),

    nr_copii integer,
    --b_strain boolean,
    b_bugetar boolean,
    b_politic boolean,

    start_date date default now(),
    update_date date default now(),
    end_date date
);


create sequence p_persoana_seq start 1;



