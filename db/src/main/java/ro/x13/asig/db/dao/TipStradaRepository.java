package ro.x13.asig.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.x13.asig.db.dao.domain.Tara;
import ro.x13.asig.db.dao.domain.TipStrada;

import java.util.Arrays;
import java.util.List;


public interface TipStradaRepository extends JpaRepository<TipStrada, Long> {

    List<TipStrada> findAllByOrderByNameAsc();
}

