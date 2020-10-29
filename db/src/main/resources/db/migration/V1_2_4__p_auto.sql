create table p_auto (
    id integer primary key,
    idpolita integer,

    marca integer,
    model national character varying (100),
    categorie integer,
    tip integer,
    stare_matric integer,
    nr_matric national character varying (20),
    serie_sasiu national character varying (20),
    serie_civ national character varying (20),
    nr_locuri integer,
    masa_max integer,
    cilindree integer,
    putere integer,
    an_fabricatie integer,
    combustibil integer,
    utilizare integer,

    start_date date default now(),
    update_date date default now(),
    end_date date
);


create sequence p_persoana_seq start 1;



