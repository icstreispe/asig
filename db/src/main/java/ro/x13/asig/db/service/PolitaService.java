package ro.x13.asig.db.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.PolitaRepository;
import ro.x13.asig.db.dao.domain.Polita;
import ro.x13.asig.db.view.model.PolitaModel;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

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
        return politaRepository.findAllByOrderById();
    }


    public Polita load(Long id) {
        return politaRepository.findById(id).get();
    }


    public List<Polita> findAll(Polita polita) {

        ExampleMatcher matcher = ExampleMatcher.matchingAll()   //custom filtering
                .withMatcher("id", exact());
        Example<Polita> filter = Example.of(polita, matcher);
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return politaRepository.findAll(filter, sort);
    }
}
