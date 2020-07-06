package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.ImobilRepository;
import ro.x13.asig.db.dao.domain.Imobil;
import ro.x13.asig.db.view.model.ImobilModel;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ImobilService {

    @Autowired
    private ImobilRepository imobilRepository;


    @Transactional
    public void save(Imobil imobil) {
        imobilRepository.save(imobil);
    }


    public List<Imobil> list() {
        return imobilRepository.findAllByOrderById();
    }


    public Imobil load(Long id) {
        return imobilRepository.findById(id).get();
    }


}
