package ro.x13.asig.db.dao;

import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.Persoana;
import ro.x13.asig.db.dao.domain.Unitate;

import java.util.List;


public interface UnitateRepository extends CrudRepository<Unitate, Long> {

    List<Unitate> findAllByOrderByNameAsc();
}

