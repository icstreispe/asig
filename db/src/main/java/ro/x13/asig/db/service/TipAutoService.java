package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.biz.CategorieAuto;
import ro.x13.asig.db.dao.biz.CategorieAutoRepository;
import ro.x13.asig.db.dao.biz.TipAuto;
import ro.x13.asig.db.dao.biz.TipAutoRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;

@Service
public class TipAutoService extends CatalogService <TipAuto>{

    @Autowired
    private TipAutoRepository repository;

    @Override
    public CatalogRepository<TipAuto> getRepo() {
        return repository;
    }
}
