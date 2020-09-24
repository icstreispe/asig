package ro.x13.asig.db.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.Polita;
import ro.x13.asig.db.dao.domain.Unitate;

import java.util.List;
import java.util.Optional;


public interface PolitaRepository extends JpaRepository<Polita, Long> {
    Optional<Polita> findBySerieAndNr(String serie, Integer nr);

    List<Polita> findAllByOrderById();

}

