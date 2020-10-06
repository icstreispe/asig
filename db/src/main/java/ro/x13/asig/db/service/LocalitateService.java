package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.LocalitateRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.Localitate;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@RequiredArgsConstructor
public class LocalitateService extends CatalogService<Localitate>{

    private final LocalitateRepository repository;


    @Override
    public CatalogRepository<Localitate> getRepo() {
        return repository;
    }


    public List<Localitate> findAll(Localitate localitate) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()   //custom filtering
                .withMatcher("id", exact())
                .withMatcher("judet", exact());
        Example<Localitate> filter = Example.of(localitate, matcher);
        return repository.findAll(filter, Sort.by(Sort.Direction.ASC, "apartenenta")
                .and(Sort.by(Sort.Direction.ASC, "name")));
    }
}
