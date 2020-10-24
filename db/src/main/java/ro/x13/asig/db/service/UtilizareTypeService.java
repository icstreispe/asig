package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.biz.UtilizareTypeRepository;
import ro.x13.asig.db.dao.biz.UtilizareType;

@Service
public class UtilizareTypeService extends CatalogService <UtilizareType>{

    @Autowired
    private UtilizareTypeRepository repository;

    @Override
    public CatalogRepository<UtilizareType> getRepo() {
        return repository;
    }
}
