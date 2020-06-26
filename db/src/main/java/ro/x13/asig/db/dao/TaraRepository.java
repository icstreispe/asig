package ro.x13.asig.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TaraRepository extends JpaRepository<Tara, Long> {

    @Query("select u from Tara u where u.endDate is null order by u.name")
    List<Tara> findAll();
}

