package ro.x13.asig.db.service.meta;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.AutoRepository;
import ro.x13.asig.db.dao.domain.Auto;
import ro.x13.asig.db.service.CrudService;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@RequiredArgsConstructor
public class MenuService extends CrudService<Auto> {

    private final AutoRepository repository;

    @Override
    public CrudRepository getRepo() {
        return repository;
    }

    @Override
    public Class getType() {
        return Auto.class;
    }


}
