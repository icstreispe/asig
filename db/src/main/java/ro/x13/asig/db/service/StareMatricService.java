package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.biz.StareMatric;
import ro.x13.asig.db.dao.biz.StareMatricRepository;
import ro.x13.asig.db.dao.biz.UtilizareType;
import ro.x13.asig.db.dao.biz.UtilizareTypeRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;

@Service
public class StareMatricService extends CatalogService <StareMatric>{

    @Autowired
    private StareMatricRepository repository;

    @Override
    public CatalogRepository<StareMatric> getRepo() {
        return repository;
    }
}
