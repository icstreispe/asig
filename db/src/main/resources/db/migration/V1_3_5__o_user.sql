create table o_user (
    id integer primary key,

    idang integer,
    idunitate integer,
    idrol integer,
    b_default boolean,

    start_date date default now(),
    update_date date default now(),
    end_date date
);

create sequence o_user_seq start with 1;