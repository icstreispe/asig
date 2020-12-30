create table m_action_flow (
    id integer primary key,

    start_action integer,
    end_action integer,
    type integer default 1,

    start_date date default now(),
    update_date date default now(),
    end_date date
);

