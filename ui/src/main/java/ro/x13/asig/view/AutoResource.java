package ro.x13.asig.view;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ro.x13.asig.view.AutoResource.AUTO_URL;
import static ro.x13.asig.view.ViewUtil.redirect;
import static ro.x13.asig.view.model.AutoModel.MODEL_NAME;

@Controller
@RequiredArgsConstructor
@RequestMapping(AUTO_URL)
public class AutoResource {

    public static final String AUTO_URL = "/auto";
    private final AutoService autoService;
    private final CategorieAutoService categorieAutoService;
    private final TipAutoService tipAutoService;
    private final MarcaService marcaService;
    private final CombustibilService combustibilService;
    private final UtilizareTypeService utilizareTypeService;
    private final StareMatricService stareMatricService;


    @RequestMapping(value = "/list", method = {GET, POST})
    public String list(Model model, AutoModel autoModel) {
        autoModel.computePage();
        Auto auto = buildDomain(autoModel);
        Page<Auto> autoList = autoService.listAll(auto, autoModel.getPage(), 7);
        List<Map> list = ServiceUtil.getList(autoList, this::toView);
        autoModel.setList(list);

        getCombos(autoModel);

        model.addAttribute(MODEL_NAME, autoModel);
        return "admin/auto.list";
    }


    @GetMapping(value = {"", "/{id}"})
    public String form(Model model, AutoModel autoModel) {
        Auto auto = autoService.load(autoModel.getId());
        toModel(autoModel, auto);

        getCombos(autoModel);

        model.addAttribute(MODEL_NAME, autoModel);
        return "admin/auto.form";
    }


    @PostMapping(value = "")
    public String save(AutoModel autoModel) {
        Auto auto = buildDomain(autoModel);
        autoService.save(auto);
        return redirect(AUTO_URL + "/list");
    }


    @PostMapping(value = "/ajax", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public AutoModel ajax(Long categorieAuto) {   //matches id/name of the field!!!
        List<TextValueModel> tipAutoList = tipAutoService.listCombo(categorieAuto);

        AutoModel model = new AutoModel();
        model.setTipAutoList(tipAutoList);
        return model;
    }


    private void getCombos(AutoModel autoModel) {
        autoModel.setCategorieAutoList(categorieAutoService.listCombo());
        autoModel.setMarcaList(marcaService.listCombo());
        autoModel.setTipAutoList(tipAutoService.listCombo());
        autoModel.setCombustibilList(combustibilService.listCombo());
        autoModel.setUtilizareList(utilizareTypeService.listCombo());
        autoModel.setStareMatricList(stareMatricService.listCombo());
    }

    private List<Map> getList() {
        Iterable<Auto> list = autoService.list();
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

    private void toModel(AutoModel model, Auto auto) {
        model.setId(auto.getId());
        model.setAnFabricatie(auto.getAnFabricatie());
        model.setCilindree(auto.getCilindree());
        model.setCategorieAuto(auto.getCategorieAuto() == null ? null : auto.getCategorieAuto().getId());
        model.setTipAuto(auto.getTipAuto() == null ? null : auto.getTipAuto().getId());
        model.setMarca(auto.getMarca() == null ? null : auto.getMarca().getId());
        model.setStareMatric(auto.getStareMatric() == null ? null : auto.getStareMatric().getId());
        model.setUtilizare(auto.getUtilizare() == null ? null : auto.getUtilizare().getId());
        model.setCombustibil(auto.getCombustibil() == null ? null : auto.getCombustibil().getId());
        model.setMasaMax(auto.getMasaMax());
        model.setModel(auto.getModel());
        model.setNrLocuri(auto.getNrLocuri());
        model.setNrMatric(auto.getNrMatric());
        model.setSerieCiv(auto.getSerieCiv());
        model.setSerieSasiu(auto.getSerieSasiu());
        model.setPutere(auto.getPutere());
    }
}
