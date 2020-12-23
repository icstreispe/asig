create table m_action (
    id integer primary key,

    code national character varying (100),
    name national character varying (100),

    start_date date default now(),
    update_date date default now(),
    end_date date
);

