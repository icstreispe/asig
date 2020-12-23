package ro.x13.asig.db.dao.meta;

import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.meta.Action;

public interface ActionRepository extends CatalogRepository<Action> {

    Action getByCode(String code);
}
