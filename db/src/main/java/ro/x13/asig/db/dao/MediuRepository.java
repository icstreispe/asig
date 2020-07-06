package ro.x13.asig.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.x13.asig.db.dao.domain.ConstructieType;
import ro.x13.asig.db.dao.domain.MediuType;

import java.util.List;


public interface MediuRepository extends JpaRepository<MediuType, Long> {

    List<MediuType> findAllByOrderByNameAsc();
}

