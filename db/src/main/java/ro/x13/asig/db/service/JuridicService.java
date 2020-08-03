package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.catalog.JudetRepository;
import ro.x13.asig.db.dao.catalog.JuridicRepository;
import ro.x13.asig.db.dao.domain.Judet;
import ro.x13.asig.db.dao.domain.Juridic;

@Service
public class JuridicService extends CatalogService <Juridic>{

    @Autowired
    private JuridicRepository repository;

    @Override
    public CatalogRepository<Juridic> getRepo() {
        return repository;
    }
}
