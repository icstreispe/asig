package ro.x13.asig.db.service;

import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.domain.CatalogDomain;
import ro.x13.asig.db.view.model.TextValueModel;

import javax.transaction.Transactional;
import java.util.List;

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
        List<T> domainList = getRepo().findAllByOrderByNameAsc();
        return ServiceUtil.getTextValueModelList(domainList);
    }



}
