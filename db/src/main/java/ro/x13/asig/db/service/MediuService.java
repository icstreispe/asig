package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.catalog.MediuRepository;
import ro.x13.asig.db.dao.domain.MediuType;

@Service
public class MediuService extends CatalogService <MediuType>{

    @Autowired
    private MediuRepository mediuRepository;

    @Override
    public CatalogRepository<MediuType> getRepo() {
        return mediuRepository;
    }



}
