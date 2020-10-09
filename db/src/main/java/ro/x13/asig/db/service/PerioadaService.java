package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.MonedaRepository;
import ro.x13.asig.db.dao.PerioadaRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.Moneda;
import ro.x13.asig.db.dao.domain.Perioada;

@Service
public class PerioadaService extends CatalogService<Perioada> {

    @Autowired
    private PerioadaRepository repository;

    @Override
    public CatalogRepository<Perioada> getRepo() {
        return repository;
    }

}
