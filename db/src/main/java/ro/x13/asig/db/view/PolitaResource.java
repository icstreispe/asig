package ro.x13.asig.db.view;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.dao.domain.Moneda;
import ro.x13.asig.db.dao.domain.Polita;
import ro.x13.asig.db.dao.domain.Societate;
import ro.x13.asig.db.service.MonedaService;
import ro.x13.asig.db.service.PolitaService;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.service.SocietateService;
import ro.x13.asig.db.view.model.PolitaModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.x13.asig.db.service.DateUtil.format;

@Controller
@RequiredArgsConstructor
@RequestMapping("/polita")
public class PolitaResource {

    private final PolitaService politaService;
    private final SocietateService asigService;
    private final MonedaService monedaService;


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


    @PostMapping(value="")
    public String save(PolitaModel politaModel) {
        Polita polita = buildDomain(politaModel);
        politaService.save(polita);
        return "redirect:/polita/list";
    }

    private void getCombos(Model model, PolitaModel politaModel) {
        politaModel.setSocietateList(asigService.listComboSocAsig());
        model.addAttribute("societateList", politaModel.getSocietateList());
        politaModel.setMonedaList(monedaService.listCombo());
        model.addAttribute("monedaList", politaModel.getMonedaList());
    }

    private List<Map> getList() {
        List<Polita> list = politaService.list();
        return ServiceUtil.getList(list, this::toView);
    }

    private Polita buildDomain(PolitaModel politaModel) {
        Societate societate = asigService.get(politaModel.getSocietate());
        Moneda moneda = monedaService.get(politaModel.getMoneda());
        return Polita.builder()
                .id(politaModel.getId())
                .serie(politaModel.getSerie())
                .nr(politaModel.getNr())
                .societate(societate)
                .moneda(moneda)
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
        m.put("sumaAsig", polita.getSumaAsig());
        m.put("moneda", polita.getMoneda() == null ? null : polita.getMoneda().getName());
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
                .sumaAsig(polita.getSumaAsig())
                .moneda(polita.getMoneda() == null ? null : polita.getMoneda().getId())
                .tipPlata(polita.getTipPlata())
                .emisLa(polita.getEmisLa())
                .endValid(polita.getEndValid())
                .startValid(polita.getStartValid())
                .build();
    }
}
