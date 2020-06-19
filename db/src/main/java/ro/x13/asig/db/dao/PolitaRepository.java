package ro.x13.asig.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PolitaRepository extends JpaRepository<Polita, Long> {
    Optional<Polita> findBySerieAndNr(String serie, Integer nr);
}

