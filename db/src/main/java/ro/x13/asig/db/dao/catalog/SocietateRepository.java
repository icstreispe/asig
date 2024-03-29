package ro.x13.asig.db.dao.catalog;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.x13.asig.db.dao.domain.org.Societate;
import ro.x13.asig.db.dao.domain.Juridic;
import ro.x13.asig.db.dao.domain.org.SocietateType;

import java.util.List;


public interface SocietateRepository extends CatalogRepository<Societate> {

    List<Societate> findAllByJuridic(Juridic j);

    @Query(value = "select u from user u where cast(userNo as char) like :userNo%", nativeQuery = true)
    List<Societate> find(@Param("userNo") String userNo);

    List<Societate> findByTip(SocietateType tip);
}

