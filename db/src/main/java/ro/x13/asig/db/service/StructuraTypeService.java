package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.StructuraTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StructuraTypeService implements ComboService {

    private final StructuraTypeRepository structuraTypeRepository;

    @Override
    public List<TextValueModel> listCombo() {
        return structuraTypeRepository.findAll().stream()
                .map(s -> TextValueModel.builder()
                        .text(s.getName())
                        .value("" + s.getId())
                        .build())
                .collect(Collectors.toList());
    }
}