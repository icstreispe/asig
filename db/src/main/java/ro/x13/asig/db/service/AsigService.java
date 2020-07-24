package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.AsigRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.Asig;

@Service
public class AsigService extends CatalogService<Asig> {

    @Autowired
    private AsigRepository repository;

    @Override
    public CatalogRepository<Asig> getRepo() {
        return repository;
    }


}
