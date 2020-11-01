package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.UnitateRepository;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.org.Unitate;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
@RequiredArgsConstructor
public class UnitateService extends CatalogService<Unitate> {

    private final UnitateRepository repository;

    @Override
    public CatalogRepository<Unitate> getRepo() {
        return repository;
    }


    public List<Unitate> list(Unitate u) {

        ExampleMatcher matcher = ExampleMatcher.matchingAll()   //custom filtering for 2 properties
                .withMatcher("name", startsWith());
        Example<Unitate> filter = Example.of(u, matcher);
        return repository.findAll(filter);
    }
}
