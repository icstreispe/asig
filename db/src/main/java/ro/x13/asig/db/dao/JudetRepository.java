package ro.x13.asig.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.x13.asig.db.dao.domain.Judet;

import java.util.List;


public interface JudetRepository extends JpaRepository<Judet, Long> {

    List<Judet> findAllByOrderByNameAsc();
}

