package ro.x13.asig.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.x13.asig.db.dao.biz.*;
import ro.x13.asig.db.dao.domain.Auto;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.service.*;
import ro.x13.asig.db.view.model.TextValueModel;
import ro.x13.asig.view.model.AutoModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auto")
public class AutoResource {

    private final AutoService autoService;
    private final CategorieAutoService categorieAutoService;
    private final TipAutoService tipAutoService;
    private final MarcaService marcaService;
    private final CombustibilService combustibilService;
    private final UtilizareTypeService utilizareTypeService;
    private final StareMatricService stareMatricService;


    @GetMapping(value = "/list")
    public String list(Model model) {
        AutoModel autoModel = new AutoModel();
        List<Auto> autoList = autoService.list();
        List<Map> list = ServiceUtil.getList(autoList, this::toView);
        autoModel.setList(list);

        getCombos(model, autoModel);

        model.addAttribute("auto", autoModel);
        return "admin/auto.list";
    }

    @PostMapping(value = "/list")
    public String filter(Model model, AutoModel autoModel) {
        Auto auto = buildDomain(autoModel);
        List<Auto> autoList = autoService.findAll(auto);
        List<Map> list = ServiceUtil.getList(autoList, this::toView);
        autoModel.setList(list);

        getCombos(model, autoModel);

        model.addAttribute("auto", autoModel);
        return "admin/auto.list";
    }


    @GetMapping(value = "")
    public String add(AutoModel autoModel, Model model) {
        autoModel.setList(getList());

        getCombos(model, autoModel);

        model.addAttribute("auto", autoModel);
        return "admin/auto.form";
    }


    @GetMapping(value = "/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Auto auto = autoService.load(id);
        AutoModel autoModel = toModel(auto);

        getCombos(model, autoModel);

        model.addAttribute("auto", autoModel);
        return "admin/auto.form";
    }


    @PostMapping(value = "")
    public String save(AutoModel autoModel) {
        Auto auto = buildDomain(autoModel);
        autoService.save(auto);
        return "redirect:/auto/list";
    }


    @PostMapping(value = "/ajax", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public AutoModel ajax(Long categAutoId) {
        List<TextValueModel> tipAutoList = tipAutoService.listCombo(categAutoId);

        AutoModel model = new AutoModel();
        model.setTipAutoList(tipAutoList);
        return model;
    }


    private void getCombos(Model model, AutoModel autoModel) {
        autoModel.setCategorieAutoList(categorieAutoService.listCombo());
        autoModel.setMarcaList(marcaService.listCombo());
        autoModel.setTipAutoList(tipAutoService.listCombo());
        autoModel.setCombustibilList(combustibilService.listCombo());
        autoModel.setUtilizareList(utilizareTypeService.listCombo());
        autoModel.setStareMatricList(stareMatricService.listCombo());
    }

    private List<Map> getList() {
        List<Auto> list = autoService.list();
        return ServiceUtil.getList(list, this::toView);
    }

    private Auto buildDomain(AutoModel autoModel) {
        CategorieAuto categorieAuto = categorieAutoService.get(autoModel.getCategorieAuto());
        TipAuto tipAuto = tipAutoService.get(autoModel.getTipAuto());
        Combustibil combustibil = combustibilService.get(autoModel.getCombustibil());
        UtilizareType utilizare = utilizareTypeService.get(autoModel.getUtilizare());
        Marca marca = marcaService.get(autoModel.getMarca());
        StareMatric stareMatric = stareMatricService.get(autoModel.getStareMatric());
        return Auto.builder()
                .id(autoModel.getId())
                .anFabricatie(autoModel.getAnFabricatie())
                .cilindree(autoModel.getCilindree())
                .categorieAuto(categorieAuto)
                .tipAuto(tipAuto)
                .combustibil(combustibil)
                .utilizare(utilizare)
                .marca(marca)
                .stareMatric(stareMatric)
                .masaMax(autoModel.getMasaMax())
                .model(autoModel.getModel())
                .nrLocuri(autoModel.getNrLocuri())
                .nrMatric(autoModel.getNrMatric())
                .serieCiv(autoModel.getSerieCiv())
                .serieSasiu(autoModel.getSerieSasiu())
                .putere(autoModel.getPutere())
                .build();
    }

    private Map toView(Domain domain) {
        Auto auto = (Auto) domain;
        Map m = new HashMap();
        m.put("id", auto.getId());
        m.put("model", auto.getModel());
        m.put("marca", auto.getMarca() == null ? null : auto.getMarca().getName());
        m.put("categorieAuto", auto.getCategorieAuto() == null ? null : auto.getCategorieAuto().getName());
        m.put("tipAuto", auto.getTipAuto() == null ? null : auto.getTipAuto().getName());
        m.put("stareMatric", auto.getStareMatric() == null ? null : auto.getStareMatric().getName());
        m.put("utilizare", auto.getUtilizare() == null ? null : auto.getUtilizare().getName());
        m.put("combustibil", auto.getCombustibil() == null ? null : auto.getCombustibil().getName());
        m.put("stareMatric", auto.getStareMatric() == null ? null : auto.getStareMatric().getName());
        m.put("nrMatric", auto.getNrMatric());
        m.put("serieCiv", auto.getSerieCiv());
        m.put("serieSasiu", auto.getSerieSasiu());
        m.put("anFabricatie", auto.getAnFabricatie());
        m.put("cilindree", auto.getCilindree());
        m.put("masaMax", auto.getMasaMax());
        m.put("putere", auto.getPutere());
        m.put("nrLocuri", auto.getNrLocuri());

        return m;
    }

    private AutoModel toModel(Auto auto) {
        return AutoModel.builder()
                .id(auto.getId())
                .anFabricatie(auto.getAnFabricatie())
                .cilindree(auto.getCilindree())
                .categorieAuto(auto.getCategorieAuto() == null ? null : auto.getCategorieAuto().getId())
                .tipAuto(auto.getTipAuto() == null ? null : auto.getTipAuto().getId())
                .marca(auto.getMarca() == null ? null : auto.getMarca().getId())
                .stareMatric(auto.getStareMatric() == null ? null : auto.getStareMatric().getId())
                .utilizare(auto.getUtilizare() == null ? null : auto.getUtilizare().getId())
                .combustibil(auto.getCombustibil() == null ? null : auto.getCombustibil().getId())
                .masaMax(auto.getMasaMax())
                .model(auto.getModel())
                .nrLocuri(auto.getNrLocuri())
                .nrMatric(auto.getNrMatric())
                .serieCiv(auto.getSerieCiv())
                .serieSasiu(auto.getSerieSasiu())
                .putere(auto.getPutere())
                .build();
    }
}
