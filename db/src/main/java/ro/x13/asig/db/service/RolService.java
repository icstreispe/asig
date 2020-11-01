package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.RolRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.org.Rol;

@Service
@RequiredArgsConstructor
public class RolService extends CatalogService<Rol> {

    private final RolRepository repository;

    @Override
    public CatalogRepository<Rol> getRepo() {
        return repository;
    }


}
