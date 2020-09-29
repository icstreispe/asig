package ro.x13.asig.db.dao.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import java.util.List;


@NoRepositoryBean
public interface  CatalogRepository<T extends CatalogDomain> extends JpaRepository<T, Long> {

    List<T> findAllByOrderByNameAsc();
}

