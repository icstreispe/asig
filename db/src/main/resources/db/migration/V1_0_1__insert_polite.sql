insert into polite (id_polita, serie, nr, start_valid, end_valid, emis_la, tip_plata)
values (1, 'RR', 123, now() - interval '1 day', now() - interval '1 year', now(), 1);

commit;


