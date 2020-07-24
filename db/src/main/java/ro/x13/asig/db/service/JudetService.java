package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.catalog.JudetRepository;
import ro.x13.asig.db.dao.domain.Judet;

@Service
public class JudetService extends CatalogService <Judet>{

    @Autowired
    private JudetRepository judetRepository;

    @Override
    public CatalogRepository<Judet> getRepo() {
        return judetRepository;
    }
}
