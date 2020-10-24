package ro.x13.asig.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.x13.asig.db.dao.domain.Auto;

import java.util.List;


public interface AutoRepository extends JpaRepository<Auto, Long> {

    List<Auto> findAllByOrderById();
}

