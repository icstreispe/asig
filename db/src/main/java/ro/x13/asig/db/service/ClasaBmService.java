package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.AsigTypeRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.catalog.ClasaBmRepository;
import ro.x13.asig.db.dao.domain.AsigType;
import ro.x13.asig.db.dao.domain.biz.ClasaBm;

@Service
public class ClasaBmService extends CatalogService <ClasaBm>{

    @Autowired
    private ClasaBmRepository repository;

    @Override
    public CatalogRepository<ClasaBm> getRepo() {
        return repository;
    }
}
