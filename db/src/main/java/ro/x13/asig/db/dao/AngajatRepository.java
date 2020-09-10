package ro.x13.asig.db.dao;

import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.Angajat;
import ro.x13.asig.db.dao.domain.Persoana;

import java.util.List;


public interface AngajatRepository extends CrudRepository<Angajat, Long> {

    List<Angajat> findAllByOrderByCnpAsc();
}

