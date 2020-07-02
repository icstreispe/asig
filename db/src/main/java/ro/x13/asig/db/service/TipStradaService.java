package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.TipStradaRepository;
import ro.x13.asig.db.dao.domain.TipStrada;
import ro.x13.asig.db.service.model.TextValueModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipStradaService extends CatalogService<TipStrada> {

    @Autowired
    private TipStradaRepository tipStradaRepository;

    @Override
    public JpaRepository<TipStrada, Long> getRepo() {
        return tipStradaRepository;
    }

    @Override
    public List<TextValueModel> listCombo() {
        List<TextValueModel> list = tipStradaRepository.findAllByOrderByNameAsc().stream()
                .map(s -> TextValueModel.builder()
                        .text(s.getName())
                        .value("" + s.getId())
                        .build())
                .collect(Collectors.toList());
        list.add(0, TextValueModel.builder().text("-").value("").build());
        return list;
    }



}
