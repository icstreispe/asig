package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.MonedaRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.Moneda;

@Service
public class MonedaService extends CatalogService<Moneda> {

    @Autowired
    private MonedaRepository repository;

    @Override
    public CatalogRepository<Moneda> getRepo() {
        return repository;
    }

}
