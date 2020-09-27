package ro.x13.asig.db.dao;

import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.Moneda;
import ro.x13.asig.db.dao.domain.Rol;

import java.util.List;


public interface MonedaRepository extends CatalogRepository<Moneda> {

    List<Moneda> findAllByOrderByNameAsc();
}

