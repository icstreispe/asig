package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.RolRepository;
import ro.x13.asig.db.dao.UnitateRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.Rol;
import ro.x13.asig.db.dao.domain.Unitate;

@Service
@RequiredArgsConstructor
public class UnitateService extends CatalogService<Unitate> {

    private final UnitateRepository repository;

    @Override
    public CatalogRepository<Unitate> getRepo() {
        return repository;
    }


}
