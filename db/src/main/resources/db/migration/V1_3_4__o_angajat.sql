create table o_angajat (
    id integer primary key,

    cnp national character varying (15),
    cod national character varying (15),
    username national character varying (100),
    --tip integer,
    idsoc integer,
    idpass integer,

    nume national character varying (100),
    prenume national character varying (100),
    ci_serie national character varying (20),
    ci_numar national character varying (20),

    telefon national character varying (20),
    email national character varying (100),

    start_date date default now(),
    update_date date default now(),
    end_date date
);

create sequence o_angajat_seq start with 1;