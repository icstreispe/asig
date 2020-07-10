package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.TaraRepository;
import ro.x13.asig.db.dao.TaraRepositoryImpl;
import ro.x13.asig.db.dao.domain.Tara;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;
import java.util.stream.Collectors;

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

    public Tara get(Long id) {
        return id != null ? taraRepository.getOne(id) : null;
    }

    @Override
    public JpaRepository<Tara, Long> getRepo() {
        return null;
    }


}
