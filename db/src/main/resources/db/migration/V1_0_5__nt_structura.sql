create table nt_structura (
    id integer primary key,
    name national character varying (100),
    start_date date default now(),
    update_date date default now(),
    end_date date
);


insert into nt_structura (id, name, start_date, end_date)
values (1, 'Beton/Prefabricate', now(), null);
insert into nt_structura (id, name, start_date, end_date)
values (2, 'Caramida/Piatra', now(), null);
insert into nt_structura (id, name, start_date, end_date)
values (3, 'Metal', now(), null);
insert into nt_structura (id, name, start_date, end_date)
values (4, 'Lemn', now(), null);
insert into nt_structura (id, name, start_date, end_date)
values (5, 'Paianta/Chirpici', now(), null);

commit;


