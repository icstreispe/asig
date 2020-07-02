package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.TaraRepository;
import ro.x13.asig.db.dao.domain.Tara;
import ro.x13.asig.db.service.model.TextValueModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaraService extends CatalogService<Tara> {

    private final TaraRepository taraRepository;

    @Override
    public List<TextValueModel> listCombo() {
        List<TextValueModel> list = taraRepository.findAll().stream()
                .map(s -> TextValueModel.builder()
                        .text(s.getName())
                        .value("" + s.getId())
                        .build())
                .collect(Collectors.toList());
        list.add(0, TextValueModel.builder().text("-").value("").build());
        return list;
    }

    @Override
    public JpaRepository<Tara, Long> getRepo() {
        return taraRepository;
    }


}
