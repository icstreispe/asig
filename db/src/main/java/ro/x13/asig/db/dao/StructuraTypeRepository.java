package ro.x13.asig.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.x13.asig.db.dao.domain.StructuraType;

import java.util.List;


public interface StructuraTypeRepository extends JpaRepository<StructuraType, Long> {
    List<StructuraType> findAll();
}

