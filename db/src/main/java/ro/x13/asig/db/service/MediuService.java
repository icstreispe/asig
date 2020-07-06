package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.ConstructieRepository;
import ro.x13.asig.db.dao.MediuRepository;
import ro.x13.asig.db.dao.domain.ConstructieType;
import ro.x13.asig.db.dao.domain.MediuType;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediuService extends CatalogService <MediuType>{

    @Autowired
    private MediuRepository mediuRepository;

    @Override
    public JpaRepository<MediuType, Long> getRepo() {
        return mediuRepository;
    }

    @Override
    public List<TextValueModel> listCombo() {
        List<TextValueModel> list = mediuRepository.findAllByOrderByNameAsc().stream()
                .map(s -> TextValueModel.builder()
                        .text(s.getName())
                        .value("" + s.getId())
                        .build())
                .collect(Collectors.toList());
        list.add(0, TextValueModel.builder().text("-").value("").build());
        return list;
    }

}
