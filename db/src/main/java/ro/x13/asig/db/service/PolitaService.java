package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.PolitaRepository;
import ro.x13.asig.db.dao.domain.Polita;
import ro.x13.asig.db.view.model.PolitaModel;

import javax.transaction.Transactional;
import java.util.List;
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

    @Transactional
    public void save(Polita polita) {
        politaRepository.save(polita);
    }


    public List<Polita> list() {
        return politaRepository.findAll();
    }


    public Polita load(Long id) {
        return politaRepository.findById(id).get();
    }


}
