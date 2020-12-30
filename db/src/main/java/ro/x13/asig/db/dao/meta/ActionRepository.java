package ro.x13.asig.db.dao.meta;

import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.meta.Action;

import java.util.List;

public interface ActionRepository extends CatalogRepository<Action> {

    Action getByCode(String code);

    List<Action> findAllByTypeOrderByNameAsc(long type);
}
