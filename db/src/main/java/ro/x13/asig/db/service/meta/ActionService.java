package ro.x13.asig.db.service.meta;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.domain.meta.Action;
import ro.x13.asig.db.dao.meta.ActionRepository;
import ro.x13.asig.db.service.CatalogService;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionService extends CatalogService<Action> {

    private final ActionRepository repository;

    @Override
    public ActionRepository getRepo() {
        return repository;
    }


    public Action get(String code){
        return repository.getByCode(code);
    }

    @Override
    public List<TextValueModel> listCombo() {
        List<Action> domainList = getRepo().findAllByTypeOrderByNameAsc(1l);
        return ServiceUtil.getTextValueModelList(domainList);
    }


}
