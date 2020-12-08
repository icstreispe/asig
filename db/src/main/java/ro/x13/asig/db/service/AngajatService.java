package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.AngajatRepository;
import ro.x13.asig.db.dao.domain.org.Angajat;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
public class AngajatService extends CrudService<Angajat> {

    @Autowired
    private AngajatRepository repository;

    @Override
    public CrudRepository<Angajat, Long> getRepo() {
        return repository;
    }

    @Override
    public Class<Angajat> getType() {
        return Angajat.class;
    }

    public List<Angajat> list() {
        return repository.findAllByOrderByCnpAsc();
    }


    public List<Angajat> findAll (Angajat a){
        ExampleMatcher matcher = ExampleMatcher.matchingAll()   //custom filtering for 2 properties
                .withMatcher("cnp", startsWith())
                .withMatcher("nume", startsWith());
        Example<Angajat> filter = Example.of(a, matcher);
        return repository.findAll(filter);
    }

    public Angajat get(String username){
        return repository.findByUsername(username);
    }

    public List<TextValueModel> listCombo() {
        List<Angajat> angajatList = repository.findAllByOrderByCnpAsc();
        return ServiceUtil.getTextValueModelList(angajatList, a -> TextValueModel.builder()
                .text(a.getLabel())
                .value("" + a.getId())
                .build());
    }
}
