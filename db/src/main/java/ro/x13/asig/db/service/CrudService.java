package ro.x13.asig.db.service;

import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.BaseDomain;

import javax.transaction.Transactional;

public abstract class CrudService<T extends BaseDomain> {

    public abstract CrudRepository<T, Long> getRepo ();

    @Transactional
    public void save(T domain) {
        getRepo().save(domain);
    }


}
