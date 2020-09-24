package ro.x13.asig.db.dao.catalog;

import ro.x13.asig.db.dao.domain.CatalogDomain;

import java.util.List;


public interface CatalogRepositoryCustom<T extends CatalogDomain> {

    List<T> findAllByOrderByNameAsc();
}

