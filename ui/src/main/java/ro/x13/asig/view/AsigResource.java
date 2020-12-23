package ro.x13.asig.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.dao.domain.Polita;
import ro.x13.asig.db.service.*;
import ro.x13.asig.db.util.DateUtil;
import ro.x13.asig.view.model.AsigModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/asig")
public class AsigResource {

    private final PolitaService politaService;
    private final SocietateService societateService;
    private final MonedaService monedaService;
    private final ProdusService produsService;
    private final PerioadaService perioadaService;
    private final ClasaBmService clasaBmService;


    @GetMapping(value = "/list")
    public String list(Model model) {
        AsigModel asigModel = new AsigModel();
        List<Polita> politaList = politaService.list();
        List<Map> list = ServiceUtil.getList(politaList, this::toView);
        asigModel.setList(list);

        //getCombos(model, asigModel);

        model.addAttribute("model", asigModel);
        return "asig.list";
    }

    /*
    @PostMapping(value = "/list")
    public String filter(Model model, AsigModel politaModel) {
        Polita polita = buildDomain(politaModel);
        List<Polita> politaList = politaService.findAll(polita);
        List<Map> list = ServiceUtil.getList(politaList, this::toView);
        politaModel.setList(list);

        getCombos(model, politaModel);

        model.addAttribute("polita", politaModel);
        return "polita.list";
    }


    @GetMapping(value="")
    public String add(AsigModel politaModel, Model model) {
        politaModel.setList(getList());

        getCombos(model, politaModel);

        model.addAttribute("polita", politaModel);
        return "admin/polita.form";
    }



    @GetMapping(value="/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Asig polita = politaService.load(id);
        AsigModel politaModel = toModel(polita);

        getCombos(model, politaModel);

        model.addAttribute("polita", politaModel);
        return "admin/polita.form";
    }

    @PostMapping(value = "/ajax", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public AsigModel ajax(Long id) {

        Societate societate = societateService.get(id);
        Produs p = new Produs();
        p.setSocietate(societate);
        List<TextValueModel> produsList = produsService.listCombo(p);

        AsigModel model = new AsigModel();
        model.setProdusList(produsList);
        return model;
    }


    @PostMapping(value="")
    public String save(AsigModel politaModel) {
        Asig polita = buildDomain(politaModel);
        politaService.save(polita);
        return "redirect:/polita/list";
    }

    private void getCombos(Model model, AsigModel politaModel) {
        politaModel.setSocietateList(societateService.listComboSocAsig());

        politaModel.setMonedaList(monedaService.listCombo());
        politaModel.setProdusList(produsService.listCombo());
        politaModel.setPerioadaList(perioadaService.listCombo());
        politaModel.setClasaBmList(clasaBmService.listCombo());
    }

    private List<Map> getList() {
        List<Asig> list = politaService.list();
        return ServiceUtil.getList(list, this::toView);
    }

    private Polita buildDomain(AsigModel politaModel) {
        Societate societate = societateService.get(politaModel.getSocietate());
        Moneda moneda = monedaService.get(politaModel.getMoneda());
        Produs produs = produsService.get(politaModel.getProdus());
        Perioada perioada = perioadaService.get(politaModel.getPerioada());
        ClasaBm clasaBm = clasaBmService.get(politaModel.getClasaBm());
        return Polita.builder()
                .id(politaModel.getId())
                .serie(politaModel.getSerie())
                .nr(politaModel.getNr())
                .societate(societate)
                .moneda(moneda)
                .produs(produs)
                .perioada(perioada)
                .clasaBm(clasaBm)
                .sumaAsig(politaModel.getSumaAsig())
                .emisLa(politaModel.getEmisLa())
                .startValid(politaModel.getStartValid())
                .endValid(politaModel.getEndValid())
                .tipPlata(politaModel.getTipPlata())
                .build();
    }



    private AsigModel toModel(Asig polita) {
        return AsigModel.builder()
                .id(polita.getId())
                .serie(polita.getSerie())
                .nr(polita.getNr())
                .societate(polita.getSocietate() == null ? null : polita.getSocietate().getId())
                .produs(polita.getProdus() == null ? null : polita.getProdus().getId())
                .sumaAsig(polita.getSumaAsig())
                .moneda(polita.getMoneda() == null ? null : polita.getMoneda().getId())
                .tipPlata(polita.getTipPlata())
                .perioada(polita.getPerioada() == null ? null : polita.getPerioada().getId())
                .clasaBm(polita.getClasaBm() == null ? null : polita.getClasaBm().getId())
                .emisLa(polita.getEmisLa())
                .endValid(polita.getEndValid())
                .startValid(polita.getStartValid())
                .build();
    }
 */

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
        m.put("clasaBm", polita.getClasaBm() == null ? null : polita.getClasaBm().getName());
        m.put("startUser", polita.getStartUser().getAngajat().getNume() + " " + polita.getStartUser().getAngajat().getPrenume());
        m.put("tipPlata", polita.getTipPlata());
        m.put("emisLa", DateUtil.format(polita.getEmisLa()));
        m.put("endValid", DateUtil.format(polita.getEndValid()));
        m.put("startValid", DateUtil.format(polita.getStartValid()));

        return m;
    }
}
