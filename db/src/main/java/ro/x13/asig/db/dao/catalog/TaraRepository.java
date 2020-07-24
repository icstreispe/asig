package ro.x13.asig.db.dao.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.x13.asig.db.dao.domain.Tara;

import java.util.List;


public interface TaraRepository extends CatalogRepository<Tara> {


    //@Query("select new" + cName + " (u.name, u.id) from Tara u where u.endDate is null order by u.name")
    List<Tara> findAll();
}

