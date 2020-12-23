package ro.x13.asig.db.service.meta;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.meta.Action;
import ro.x13.asig.db.dao.meta.ActionRepository;
import ro.x13.asig.db.service.CatalogService;

@Service
@RequiredArgsConstructor
public class ActionService extends CatalogService<Action> {

    private final ActionRepository repository;

    @Override
    public CatalogRepository getRepo() {
        return repository;
    }


    public Action get(String code){
        return repository.getByCode(code);
    }

}
