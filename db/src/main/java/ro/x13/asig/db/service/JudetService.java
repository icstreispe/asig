package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.JudetRepository;
import ro.x13.asig.db.dao.domain.Judet;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudetService extends CatalogService <Judet>{

    @Autowired
    private JudetRepository judetRepository;

    @Override
    public List<TextValueModel> listCombo() {
        List<TextValueModel> list = judetRepository.findAllByOrderByNameAsc().stream()
                .map(s -> TextValueModel.builder()
                        .text(s.getName())
                        .value("" + s.getId())
                        .build())
                .collect(Collectors.toList());
        list.add(0, TextValueModel.builder().text("-").value("").build());
        return list;
    }

    @Override
    public JpaRepository<Judet, Long> getRepo() {
        return judetRepository;
    }
}
