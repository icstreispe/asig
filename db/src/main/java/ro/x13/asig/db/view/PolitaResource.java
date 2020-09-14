package ro.x13.asig.db.view;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.x13.asig.db.dao.domain.Societate;
import ro.x13.asig.db.dao.domain.Polita;
import ro.x13.asig.db.service.SocietateService;
import ro.x13.asig.db.view.model.PolitaModel;
import ro.x13.asig.db.service.PolitaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@RequestMapping("/polita")
public class PolitaResource {

    private final PolitaService politaService;
    private final SocietateService asigService;

    @GetMapping(value = "/polita/{serie}/{nr}")
    public ResponseEntity<PolitaModel> getProductByCode(@PathVariable("serie") String serie, @PathVariable("nr") Integer nr) {
        return politaService.findBySerieAndNr(serie, nr)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping(value="/polita")
    public String add(PolitaModel politaModel, Model model) {
        politaModel.setPolitaList(getList());

        getCombos(model, politaModel);

        model.addAttribute("polita", politaModel);
        return "polita";
    }



    @GetMapping(value="/polita/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Polita polita = politaService.load(id);
        PolitaModel politaModel = toModel(polita);
        politaModel.setPolitaList(getList());

        getCombos(model, politaModel);

        model.addAttribute("polita", politaModel);
        return "polita";
    }


    @PostMapping(value="/polita")
    public String save(PolitaModel politaModel) {
        Polita polita = buildPolita(politaModel);
        politaService.save(polita);
        return "redirect:/polita";
    }

    private void getCombos(Model model, PolitaModel politaModel) {
        politaModel.setSocAsigList(asigService.listCombo());

        model.addAttribute("socAsigList", politaModel.getSocAsigList());
    }

    private List<Map> getList() {
        List<Polita> list = politaService.list();
        return StreamSupport.stream(list.spliterator(), false)
                .map(a -> toView(a))       // pt list(lista()): Polita::toMap
                .collect(Collectors.toList());
    }

    private Polita buildPolita(PolitaModel politaModel) {
        Societate societate = asigService.get(politaModel.getSocAsig());
        return Polita.builder()
                .id(politaModel.getId())
                .societate(societate)
                .serie(politaModel.getSerie())
                .nr(politaModel.getNr())
                .sumaAsig(politaModel.getSumaAsig())
                .emisLa(politaModel.getEmisLa())
                .startValid(politaModel.getStartValid())
                .endValid(politaModel.getEndValid())
                .tipPlata(politaModel.getTipPlata())
                .build();
    }

    private Map toView(Polita polita) {
        Map m = new HashMap();
        m.put("id", polita.getId());
        m.put("socAsig", polita.getSocietate() == null ? null : polita.getSocietate().getName());
        m.put("tipPlata", polita.getTipPlata());
        m.put("sumaAsig", polita.getSumaAsig());
        m.put("serie", polita.getSerie());
        m.put("nr", polita.getNr());
        m.put("emisLa", polita.getEmisLa());
        m.put("endValid", polita.getEndValid());
        m.put("startValid", polita.getStartValid());

        return m;
    }

    private PolitaModel toModel(Polita polita) {
        return PolitaModel.builder()
                .id(polita.getId())
                .socAsig(polita.getSocietate() == null ? null : polita.getSocietate().getId())
                .sumaAsig(polita.getSumaAsig())
                .emisLa(polita.getEmisLa())
                .endValid(polita.getEndValid())
                .startValid(polita.getStartValid())
                .serie(polita.getSerie())
                .nr(polita.getNr())
                .tipPlata(polita.getTipPlata())
                .build();
    }


}
