package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.PolitaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolitaService {

    private final PolitaRepository politaRepository;

    public Optional<PolitaModel> findBySerieAndNr(String serie, Integer nr) {
        return politaRepository.findBySerieAndNr(serie, nr)
                .map(polita -> PolitaModel.builder()
                        .endValid(polita.getEndValid())
                        .nr(polita.getNr())
                        .serie(polita.getSerie())
                        .startValid(polita.getStartValid())
                        .build());
    }
}
