package ro.x13.asig.db.dao.catalog;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.x13.asig.db.dao.domain.Societate;
import ro.x13.asig.db.dao.domain.Juridic;

import java.util.List;


public interface AsigRepository extends CatalogRepository<Societate> {

    List<Societate> findAllByJuridic(Juridic j);

    List<Societate> findAll(Example<Societate> a);

    @Query(value = "select u from user u where cast(userNo as char) like :userNo%", nativeQuery = true)
    public List<Societate> find(@Param("userNo") String userNo);
}

