package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.catalog.ProdusRepository;
import ro.x13.asig.db.dao.catalog.SocietateTypeRepository;
import ro.x13.asig.db.dao.domain.Produs;
import ro.x13.asig.db.dao.domain.SocietateType;
import ro.x13.asig.db.dao.domain.Unitate;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
public class ProdusService extends CatalogService<Produs> {

    @Autowired
    private ProdusRepository repository;

    @Override
    public CatalogRepository<Produs> getRepo() {
        return repository;
    }

    //TODO move to repo?
    public List<Produs> list(Produs produs) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()   //custom filtering for 2 properties
                .withMatcher("name", startsWith())
                .withMatcher("societate", exact());
        Example<Produs> filter = Example.of(produs, matcher);
        return repository.findAll(filter, Sort.by(Sort.Direction.ASC, "id"));
    }

    //
    public List<TextValueModel> listCombo(Produs produs) {
        List<Produs> domainList = list(produs);
        return ServiceUtil.getTextValueModelList(domainList);
    }
}
