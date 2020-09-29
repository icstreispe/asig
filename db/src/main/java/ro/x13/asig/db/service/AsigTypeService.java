package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.AsigTypeRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.AsigType;

@Service
public class AsigTypeService extends CatalogService <AsigType>{

    @Autowired
    private AsigTypeRepository repository;

    @Override
    public CatalogRepository<AsigType> getRepo() {
        return repository;
    }
}
