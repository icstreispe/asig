
create table p_polita (
    id          integer primary key,
    emis_la     date,
    end_valid   date,
    nr          integer,
    serie       varchar(255),
    start_valid date,
    suma_asig   double precision,
    moneda      integer
    tip_plata   integer,
    idsoc       integer,
    idprodus       integer,

);

