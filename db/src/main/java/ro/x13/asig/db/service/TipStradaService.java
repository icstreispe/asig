package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.catalog.TipStradaRepository;
import ro.x13.asig.db.dao.domain.TipStrada;

@Service
public class TipStradaService extends CatalogService<TipStrada> {

    @Autowired
    private TipStradaRepository tipStradaRepository;

    @Override
    public CatalogRepository<TipStrada> getRepo() {
        return tipStradaRepository;
    }

}
