package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.StructuraTypeRepository;
import ro.x13.asig.db.dao.TaraRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaraService implements ComboService {

    private final TaraRepository taraRepository;

    @Override
    public List<TextValueModel> listCombo() {
        return taraRepository.findAll().stream()
                .map(s -> TextValueModel.builder()
                        .text(s.getName())
                        .value("" + s.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
