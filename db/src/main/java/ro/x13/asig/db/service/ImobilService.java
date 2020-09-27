package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.ImobilRepository;
import ro.x13.asig.db.dao.domain.Imobil;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
public class ImobilService {

    @Autowired
    private ImobilRepository repository;


    @Transactional
    public void save(Imobil imobil) {
        repository.save(imobil);
    }


    public List<Imobil> list() {
        return repository.findAllByOrderById();
    }

    public List<Imobil> list(Imobil imobil) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()   //custom filtering for 2 properties
                .withMatcher("constructie", exact())
                .withMatcher("mediu", exact());
        Example<Imobil> filter = Example.of(imobil, matcher);
        return repository.findAll(filter);
    }


    public Imobil load(Long id) {
        return repository.findById(id).get();
    }



}
