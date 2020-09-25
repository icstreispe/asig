create table o_rol (
    id integer primary key,

    name national character varying (100),
    descr national character varying (200),

    start_date date default now(),
    update_date date default now(),
    end_date date
);


create sequence o_rol_seq start 1;

