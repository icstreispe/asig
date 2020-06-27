package ro.x13.asig.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface JudetRepository extends JpaRepository<Judet, Long> {

    List<Judet> findAllByOrderByNameAsc();
}

