package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.biz.Combustibil;
import ro.x13.asig.db.dao.biz.CombustibilRepository;
import ro.x13.asig.db.dao.biz.Marca;
import ro.x13.asig.db.dao.biz.MarcaRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;

@Service
public class CombustibilService extends CatalogService <Combustibil>{

    @Autowired
    private CombustibilRepository repository;

    @Override
    public CatalogRepository<Combustibil> getRepo() {
        return repository;
    }
}
