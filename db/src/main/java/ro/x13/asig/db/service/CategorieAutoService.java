package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.biz.CategorieAuto;
import ro.x13.asig.db.dao.biz.CategorieAutoRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.catalog.SocietateTypeRepository;
import ro.x13.asig.db.dao.domain.SocietateType;

@Service
public class CategorieAutoService extends CatalogService <CategorieAuto>{

    @Autowired
    private CategorieAutoRepository repository;

    @Override
    public CatalogRepository<CategorieAuto> getRepo() {
        return repository;
    }
}
