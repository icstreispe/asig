package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.biz.CategorieAuto;
import ro.x13.asig.db.dao.biz.CategorieAutoRepository;
import ro.x13.asig.db.dao.biz.Marca;
import ro.x13.asig.db.dao.biz.MarcaRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;

@Service
public class MarcaService extends CatalogService <Marca>{

    @Autowired
    private MarcaRepository repository;

    @Override
    public CatalogRepository<Marca> getRepo() {
        return repository;
    }
}
