package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.catalog.ConstructieRepository;
import ro.x13.asig.db.dao.domain.ConstructieType;

@Service
public class ConstructieService extends CatalogService <ConstructieType>{

    @Autowired
    private ConstructieRepository repository;

    @Override
    public CatalogRepository<ConstructieType> getRepo() {
        return repository;
    }


}
