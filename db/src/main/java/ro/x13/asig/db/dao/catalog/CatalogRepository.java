package ro.x13.asig.db.dao.catalog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import java.util.List;


@NoRepositoryBean
public interface  CatalogRepository<T extends CatalogDomain> extends CrudRepository<T, Long> {

    List<T> findAllByOrderByNameAsc();
}

