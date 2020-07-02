package ro.x13.asig.db.dao;

import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.Adresa;

import java.util.List;


public interface AdresaRepository extends CrudRepository<Adresa, Long> {

    List<Adresa> findAllByOrderById();
}

