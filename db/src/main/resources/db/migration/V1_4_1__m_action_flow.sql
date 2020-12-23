create table m_action_flow (
    id integer primary key,

    start_action integer,
    end_action integer,

    start_date date default now(),
    update_date date default now(),
    end_date date
);

