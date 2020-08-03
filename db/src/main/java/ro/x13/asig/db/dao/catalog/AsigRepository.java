package ro.x13.asig.db.dao.catalog;

import ro.x13.asig.db.dao.domain.Asig;
import ro.x13.asig.db.dao.domain.Juridic;

import java.util.List;


public interface AsigRepository extends CatalogRepository<Asig> {

    List<Asig> findAllByJuridic(Juridic j);
}

