package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.domain.Persoana;
import ro.x13.asig.db.dao.PersoanaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersoanaService {

    @Autowired
    private PersoanaRepository persoanaRepository;


    @Transactional
    public void save(Persoana persoana) {
        persoanaRepository.save(persoana);
    }


    public List<Persoana> list() {
        return persoanaRepository.findAllByOrderByIdDesc();
    }


    public Persoana load(Long id) {
        return persoanaRepository.findById(id).get();
    }


}
