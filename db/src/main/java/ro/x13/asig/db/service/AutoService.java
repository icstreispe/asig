package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.AutoRepository;
import ro.x13.asig.db.dao.domain.Auto;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
@RequiredArgsConstructor
public class AutoService extends CrudService<Auto> {

    private final AutoRepository repository;

    @Override
    public CrudRepository getRepo() {
        return repository;
    }

    @Override
    public Class getType() {
        return Auto.class;
    }


    public Page<Auto> listAll(Auto auto, int currentPage, int pageSize) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()   //custom filtering
                .withMatcher("id", exact())
                .withMatcher("serieCiv", startsWith());
        Example<Auto> filter = Example.of(auto, matcher);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);
        return repository.findAll(filter, pageable);        //jpa repo
    }
}
