package ro.x13.asig.db.service;

import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.BaseDomain;
import ro.x13.asig.db.dao.domain.org.Angajat;

import javax.transaction.Transactional;
import java.util.Optional;

public abstract class CrudService<T extends BaseDomain> {

    public abstract CrudRepository<T, Long> getRepo ();

    public abstract Class<T> getType ();

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

    public T load(Long id) {
        if (id == null) {
            try {
                return getType().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return get(id);
    }



}
