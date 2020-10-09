package ro.x13.asig.db.view;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.x13.asig.db.dao.domain.*;
import ro.x13.asig.db.service.*;
import ro.x13.asig.db.view.model.PolitaModel;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ro.x13.asig.db.service.DateUtil.format;

@Controller
@RequiredArgsConstructor
@RequestMapping("/polita")
public class PolitaResource {

    private final PolitaService politaService;
    private final SocietateService societateService;
    private final MonedaService monedaService;
    private final ProdusService produsService;
    private final PerioadaService perioadaService;


    @GetMapping(value = "/polita/{serie}/{nr}")
    public ResponseEntity<PolitaModel> getProductByCode(@PathVariable("serie") String serie, @PathVariable("nr") Integer nr) {
        return politaService.findBySerieAndNr(serie, nr)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping(value = "/list")
    public String list(Model model) {
        PolitaModel politaModel = new PolitaModel();
        List<Polita> politaList = politaService.list();
        List<Map> list = ServiceUtil.getList(politaList, this::toView);
        politaModel.setList(list);

        getCombos(model, politaModel);

        model.addAttribute("polita", politaModel);
        return "admin/polita.list";
    }

    @PostMapping(value = "/list")
    public String filter(Model model, PolitaModel politaModel) {
        Polita polita = buildDomain(politaModel);
        List<Polita> politaList = politaService.findAll(polita);
        List<Map> list = ServiceUtil.getList(politaList, this::toView);
        politaModel.setList(list);

        getCombos(model, politaModel);

        model.addAttribute("polita", politaModel);
        return "admin/polita.list";
    }


    @GetMapping(value="")
    public String add(PolitaModel politaModel, Model model) {
        politaModel.setList(getList());

        getCombos(model, politaModel);

        model.addAttribute("polita", politaModel);
        return "admin/polita.form";
    }



    @GetMapping(value="/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Polita polita = politaService.load(id);
        PolitaModel politaModel = toModel(polita);

        getCombos(model, politaModel);

        model.addAttribute("polita", politaModel);
        return "admin/polita.form";
    }

    @PostMapping(value = "/ajax", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public PolitaModel ajx(Long id) {

        Societate societate = societateService.get(id);
        Produs p = new Produs();
        p.setSocietate(societate);
        List<TextValueModel> produsList = produsService.listCombo(p);

        PolitaModel model = new PolitaModel();
        model.setProdusList(produsList);
        return model;
    }


    @PostMapping(value="")
    public String save(PolitaModel politaModel) {
        Polita polita = buildDomain(politaModel);
        politaService.save(polita);
        return "redirect:/polita/list";
    }

    private void getCombos(Model model, PolitaModel politaModel) {
        politaModel.setSocietateList(societateService.listComboSocAsig());
        politaModel.setMonedaList(monedaService.listCombo());
        politaModel.setProdusList(produsService.listCombo());
        politaModel.setPerioadaList(perioadaService.listCombo());
    }

    private List<Map> getList() {
        List<Polita> list = politaService.list();
        return ServiceUtil.getList(list, this::toView);
    }

    private Polita buildDomain(PolitaModel politaModel) {
        Societate societate = societateService.get(politaModel.getSocietate());
        Moneda moneda = monedaService.get(politaModel.getMoneda());
        Produs produs = produsService.get(politaModel.getProdus());
        Perioada perioada = perioadaService.get(politaModel.getPerioada());
        return Polita.builder()
                .id(politaModel.getId())
                .serie(politaModel.getSerie())
                .nr(politaModel.getNr())
                .societate(societate)
                .moneda(moneda)
                .produs(produs)
                .perioada(perioada)
                .sumaAsig(politaModel.getSumaAsig())
                .emisLa(politaModel.getEmisLa())
                .startValid(politaModel.getStartValid())
                .endValid(politaModel.getEndValid())
                .tipPlata(politaModel.getTipPlata())
                .build();
    }

    private Map toView(Domain domain) {
        Polita polita = (Polita) domain;
        Map m = new HashMap();
        m.put("id", polita.getId());
        m.put("serie", polita.getSerie());
        m.put("nr", polita.getNr());
        m.put("societate", polita.getSocietate() == null ? null : polita.getSocietate().getName());
        m.put("produs", polita.getProdus() == null ? null : polita.getProdus().getName());
        m.put("sumaAsig", polita.getSumaAsig());
        m.put("moneda", polita.getMoneda() == null ? null : polita.getMoneda().getName());
        m.put("perioada", polita.getPerioada() == null ? null : polita.getPerioada().getName());
        m.put("tipPlata", polita.getTipPlata());
        m.put("emisLa", format(polita.getEmisLa()));
        m.put("endValid", format(polita.getEndValid()));
        m.put("startValid", format(polita.getStartValid()));

        return m;
    }

    private PolitaModel toModel(Polita polita) {
        return PolitaModel.builder()
                .id(polita.getId())
                .serie(polita.getSerie())
                .nr(polita.getNr())
                .societate(polita.getSocietate() == null ? null : polita.getSocietate().getId())
                .produs(polita.getProdus() == null ? null : polita.getProdus().getId())
                .sumaAsig(polita.getSumaAsig())
                .moneda(polita.getMoneda() == null ? null : polita.getMoneda().getId())
                .tipPlata(polita.getTipPlata())
                .perioada(polita.getPerioada() == null ? null : polita.getPerioada().getId())
                .emisLa(polita.getEmisLa())
                .endValid(polita.getEndValid())
                .startValid(polita.getStartValid())
                .build();
    }
}
