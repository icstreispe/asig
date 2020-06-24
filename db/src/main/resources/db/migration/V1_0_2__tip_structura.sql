create table tip_structura (
    id integer primary key,
    name national character varying (100),
    start_date date default now(),
    end_date date
);


insert into tip_structura (id, name, start_date, end_date)
values (1, 'BETON/PREFABRICATE', now(), null);
insert into tip_structura (id, name, start_date, end_date)
values (2, 'CARAMIDA/PIATRA', now(), null);
insert into tip_structura (id, name, start_date, end_date)
values (3, 'METAL', now(), null);
insert into tip_structura (id, name, start_date, end_date)
values (4, 'LEMN', now(), null);
insert into tip_structura (id, name, start_date, end_date)
values (5, 'PAIANTA/CHIRPICI', now(), null);

commit;


