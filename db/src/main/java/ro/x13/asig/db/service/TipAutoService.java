package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.biz.CategorieAuto;
import ro.x13.asig.db.dao.biz.TipAuto;
import ro.x13.asig.db.dao.biz.TipAutoRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
public class TipAutoService extends CatalogService <TipAuto>{

    @Autowired
    private TipAutoRepository repository;
    @Autowired
    private CategorieAutoService categorieAutoService;

    @Override
    public CatalogRepository<TipAuto> getRepo() {
        return repository;
    }

    //TODO move to repo?
    public List<TipAuto> list(TipAuto tipAuto) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()   //custom filtering for 2 properties
                .withMatcher("categorieAuto", exact());
        Example<TipAuto> filter = Example.of(tipAuto, matcher);
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return repository.findAll(filter, sort);
    }

    public List<TextValueModel> listCombo(Long categorieAutoId) {
        CategorieAuto categorieAuto = categorieAutoService.get(categorieAutoId);
        TipAuto tipAuto = new TipAuto();
        tipAuto.setCategorieAuto(categorieAuto);

        List<TipAuto> domainList = list(tipAuto);
        return ServiceUtil.getTextValueModelList(domainList);
    }
}
