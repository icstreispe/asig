package ro.x13.asig.db.service.meta;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.domain.meta.Action;
import ro.x13.asig.db.dao.domain.meta.ActionFlow;
import ro.x13.asig.db.dao.meta.ActionFlowRepository;
import ro.x13.asig.db.service.CrudService;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@RequiredArgsConstructor
public class ActionFlowService extends CrudService<ActionFlow> {

    private final ActionFlowRepository repository;

    @Override
    public CrudRepository getRepo() {
        return repository;
    }

    @Override
    public Class getType() {
        return ActionFlow.class;
    }

    public List<ActionFlow> list(Action startAction){

        ActionFlow af = new ActionFlow();
        af.setStartAction(startAction);

        ExampleMatcher matcher = ExampleMatcher.matchingAll()   //custom filtering
                .withMatcher("startAction", exact());
        Example<ActionFlow> filter = Example.of(af, matcher);
        Sort sort = Sort.by(Sort.Direction.ASC, "endAction.name");
        return repository.findAll(filter, sort);
    }

}
