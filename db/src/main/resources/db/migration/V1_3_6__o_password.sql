create table o_password (
    id integer primary key,

    idang integer,

    password national character varying (100),

    start_date date default now(),
    update_date date default now(),
    end_date date
);

create sequence o_password_seq start with 1;