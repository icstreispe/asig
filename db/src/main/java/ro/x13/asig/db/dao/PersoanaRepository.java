package ro.x13.asig.db.dao;

import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.Persoana;

import java.util.List;


public interface PersoanaRepository extends CrudRepository<Persoana, Long> {

    List<Persoana> findAllByOrderByCnpAsc();
}

