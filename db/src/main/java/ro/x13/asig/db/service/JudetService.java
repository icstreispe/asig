package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.JudetRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JudetService implements ComboService {

    private final JudetRepository judetRepository;

    @Override
    public List<TextValueModel> listCombo() {
        return judetRepository.findAllByOrderByNameAsc().stream()
                .map(s -> TextValueModel.builder()
                        .text(s.getName())
                        .value("" + s.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
