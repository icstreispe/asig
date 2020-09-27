package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.MonedaRepository;
import ro.x13.asig.db.dao.catalog.AsigRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.Juridic;
import ro.x13.asig.db.dao.domain.Moneda;
import ro.x13.asig.db.dao.domain.Societate;
import ro.x13.asig.db.dao.domain.SocietateType;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
public class MonedaService extends CatalogService<Moneda> {

    @Autowired
    private MonedaRepository repository;

    @Override
    public CatalogRepository<Moneda> getRepo() {
        return repository;
    }





}
