package ro.x13.asig.db.dao.meta;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.x13.asig.db.dao.domain.meta.Action;
import ro.x13.asig.db.dao.domain.meta.ActionFlow;

public interface ActionRepository extends JpaRepository<Action, Long> {

    Action getByCode(String code);
}
