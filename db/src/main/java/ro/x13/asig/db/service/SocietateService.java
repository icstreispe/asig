package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.SocietateRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.Societate;
import ro.x13.asig.db.dao.domain.Juridic;
import ro.x13.asig.db.dao.domain.SocietateType;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
public class SocietateService extends CatalogService<Societate> {

    @Autowired
    private SocietateRepository repository;

    @Override
    public CatalogRepository<Societate> getRepo() {
        return repository;
    }

    public List<Societate> findAllByJuridic (Juridic j){
        return repository.findAllByJuridic(j);
    }

    public List<Societate> findAll (Societate a){
        ExampleMatcher matcher = ExampleMatcher.matchingAll()   //custom filtering for 2 properties
                .withMatcher("cif", startsWith())
                .withMatcher("name", startsWith());
        Example<Societate> filter = Example.of(a, matcher);
        return repository.findAll(filter);
    }

    public List<TextValueModel> listComboSocAsig() {
        List<Societate> domainList = repository.findByTip(SocietateType.builder().id(1L).build());  //TODO enums?
        return ServiceUtil.getTextValueModelList(domainList);
    }


}
