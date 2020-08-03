package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.catalog.JuridicRepository;
import ro.x13.asig.db.dao.catalog.SocietateTypeRepository;
import ro.x13.asig.db.dao.domain.Juridic;
import ro.x13.asig.db.dao.domain.SocietateType;

@Service
public class SocietateTypeService extends CatalogService <SocietateType>{

    @Autowired
    private SocietateTypeRepository repository;

    @Override
    public CatalogRepository<SocietateType> getRepo() {
        return repository;
    }
}
