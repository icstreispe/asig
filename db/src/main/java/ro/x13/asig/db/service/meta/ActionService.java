package ro.x13.asig.db.service.meta;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.domain.meta.Action;
import ro.x13.asig.db.dao.domain.meta.ActionFlow;
import ro.x13.asig.db.dao.meta.ActionFlowRepository;
import ro.x13.asig.db.dao.meta.ActionRepository;
import ro.x13.asig.db.service.CrudService;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@RequiredArgsConstructor
public class ActionService extends CrudService<Action> {

    private final ActionRepository repository;

    @Override
    public CrudRepository getRepo() {
        return repository;
    }

    @Override
    public Class getType() {
        return Action.class;
    }

    public Action get(String code){
        return repository.getByCode(code);
    }

}
