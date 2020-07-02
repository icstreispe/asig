package ro.x13.asig.db.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.x13.asig.db.service.model.PolitaModel;
import ro.x13.asig.db.service.PolitaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/polita")
public class PolitaResource {

    private final PolitaService politaService;

    @GetMapping(value = "/{serie}/{nr}")
    public ResponseEntity<PolitaModel> getProductByCode(@PathVariable("serie") String serie, @PathVariable("nr") Integer nr) {
        return politaService.findBySerieAndNr(serie, nr)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
