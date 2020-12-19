package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.AutoRepository;
import ro.x13.asig.db.dao.domain.Auto;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@RequiredArgsConstructor
public class AutoService {

    private final AutoRepository repository;

    @Transactional
    public void save(Auto auto) {
        repository.save(auto);
    }


    public List<Auto> list() {
        return repository.findAllByOrderById();
    }


    //TODO hard for now
    @Transactional
    public void del(Long id) {
        Auto a = load(id);
        repository.delete(a);
    }

    public Auto load(Long id) {
        return repository.findById(id).get();
    }


    public List<Auto> findAll(Auto auto) {

        ExampleMatcher matcher = ExampleMatcher.matchingAll()   //custom filtering
                .withMatcher("id", exact());
        Example<Auto> filter = Example.of(auto, matcher);
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return repository.findAll(filter, sort);        //jpa repo
    }
}
