package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.ConstructieRepository;
import ro.x13.asig.db.dao.JudetRepository;
import ro.x13.asig.db.dao.domain.ConstructieType;
import ro.x13.asig.db.dao.domain.Judet;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConstructieService extends CatalogService <ConstructieType>{

    @Autowired
    private ConstructieRepository constructieRepository;

    @Override
    public JpaRepository<ConstructieType, Long> getRepo() {
        return constructieRepository;
    }

    @Override
    public List<TextValueModel> listCombo() {
        List<TextValueModel> list = constructieRepository.findAllByOrderByNameAsc().stream()
                .map(s -> TextValueModel.builder()
                        .text(s.getName())
                        .value("" + s.getId())
                        .build())
                .collect(Collectors.toList());
        list.add(0, TextValueModel.builder().text("-").value("").build());
        return list;
    }

}
