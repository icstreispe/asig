package ro.x13.asig.db.dao;

import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.Asig;
import ro.x13.asig.db.dao.domain.Persoana;

import java.util.List;


public interface AsigRepository extends CrudRepository<Asig, Long> {

    List<Asig> findAllByOrderByNumeAsc();
}

