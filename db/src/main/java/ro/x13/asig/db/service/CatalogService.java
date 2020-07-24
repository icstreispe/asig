package ro.x13.asig.db.service;

import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.CatalogDomain;
import ro.x13.asig.db.view.model.TextValueModel;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CatalogService<T extends CatalogDomain> {

    public abstract CatalogRepository<T> getRepo ();

    public T get(Long id) {
        return id != null ? getRepo().findById(id).get() : null;
    }

    /*public T load(Long id) {
        return getRepo().findById(id).get();
    }
     */


    public List<T> list() {
        return getRepo().findAllByOrderByNameAsc();
    }

    @Transactional
    public void save(T t) {
        getRepo().save(t);
    }

    //public abstract List<TextValueModel> listCombo();
    public List<TextValueModel> listCombo() {
        List<TextValueModel> list = getRepo().findAllByOrderByNameAsc().stream()
                .map(s -> TextValueModel.builder()
                        .text(s.getName())
                        .value("" + s.getId())
                        .build())
                .collect(Collectors.toList());
        list.add(0, TextValueModel.builder().text("-").value("").build());
        return list;
    }

}
