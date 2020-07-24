package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.catalog.CatalogRepository;
import ro.x13.asig.db.dao.catalog.TaraRepository;
import ro.x13.asig.db.dao.catalog.TaraRepositoryImpl;
import ro.x13.asig.db.dao.domain.Tara;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaraService extends CatalogService<Tara> {

    private final TaraRepository taraRepository;

    private final TaraRepositoryImpl taraRepositoryImpl;

    @Override
    public List<TextValueModel> listCombo() {
        List<TextValueModel> list = taraRepositoryImpl.findModel();
        list.add(0, TextValueModel.builder().text("-").value("").build());
        return list;
    }

    @Override
    public CatalogRepository<Tara> getRepo() {
        return taraRepository;
    }


}
