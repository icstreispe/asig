package ro.x13.asig.db.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.Imobil;

import java.util.List;


public interface ImobilRepository extends CrudRepository<Imobil, Long> {

    List<Imobil> findAllByOrderById();

    List<Imobil> findAll(Example<Imobil> a);
}

