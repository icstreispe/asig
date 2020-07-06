package ro.x13.asig.db.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;

public abstract class CatalogService<T> {
    public abstract List<TextValueModel> listCombo();

    public abstract JpaRepository<T, Long> getRepo ();

    public T get(Long id) {
        return id != null ? getRepo().getOne(id) : null;
    }

}
