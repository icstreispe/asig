package ro.x13.asig.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.x13.asig.db.dao.domain.Polita;

import java.util.List;
import java.util.Optional;


public interface PolitaRepository extends JpaRepository<Polita, Long> {
    Optional<Polita> findBySerieAndNr(String serie, Integer nr);

    List<Polita> findAllByOrderById();

}

