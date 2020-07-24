package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.StructuraType;
import ro.x13.asig.db.dao.catalog.StructuraTypeRepository;

@Service
@RequiredArgsConstructor
public class StructuraTypeService extends CatalogService<StructuraType> {

    private final StructuraTypeRepository structuraTypeRepository;


    @Override
    public CatalogRepository<StructuraType> getRepo() {
        return structuraTypeRepository;
    }
}
