package ro.x13.asig.db.service;

import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.BaseDomain;

import javax.transaction.Transactional;
import java.util.Optional;

public abstract class CrudService<T extends BaseDomain> {

    public abstract CrudRepository<T, Long> getRepo ();

    @Transactional
    public void save(T domain) {
        getRepo().save(domain);
    }

    public T get(Long id) {
        Optional<T> o = getRepo().findById(id);
        if (o.isPresent()){
            return o.get();
        }
        return null;
    }


}
