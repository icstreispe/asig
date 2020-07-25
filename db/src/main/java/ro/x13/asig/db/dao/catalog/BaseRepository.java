package ro.x13.asig.db.dao.catalog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseRepository {


    @PersistenceContext
    EntityManager em;

}
