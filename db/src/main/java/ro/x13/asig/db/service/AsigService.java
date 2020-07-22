package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.AsigRepository;
import ro.x13.asig.db.dao.domain.Asig;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AsigService {

    @Autowired
    private AsigRepository repository;


    @Transactional
    public void save(Asig asig) {
        repository.save(asig);
    }


    public List<Asig> list() {
        return repository.findAllByOrderByNumeAsc();
    }


    public Asig load(Long id) {
        return repository.findById(id).get();
    }


}
