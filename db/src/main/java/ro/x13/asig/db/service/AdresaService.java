package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.AdresaRepository;
import ro.x13.asig.db.dao.domain.Adresa;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdresaService {

    @Autowired
    private AdresaRepository adresaRepository;


    @Transactional
    public void save(Adresa adresa) {
        adresaRepository.save(adresa);
    }


    public List<Adresa> list() {
        return adresaRepository.findAllByOrderById();
    }


    public Adresa load(Long id) {
        return adresaRepository.findById(id).get();
    }


}
