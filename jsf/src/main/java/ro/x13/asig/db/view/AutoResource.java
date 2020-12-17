package ro.x13.asig.db.view;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.x13.asig.db.dao.biz.*;
import ro.x13.asig.db.dao.domain.Auto;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.filter.Cacheable;
import ro.x13.asig.db.filter.RequestScopedController;
import ro.x13.asig.db.service.*;
import ro.x13.asig.db.view.jsf.HeaderView;
import ro.x13.asig.db.view.model.AutoModel;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestScopedController("auto")
@RequiredArgsConstructor
public class AutoResource {

    private AutoModel model = new AutoModel();

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

        model.addAttribute("auto", autoModel);
        return "admin/auto.list";
    }

    @PostMapping(value = "/list")
    public String filter(Model model, AutoModel autoModel) {
        Auto auto = buildDomain(autoModel);
        List<Auto> autoList = autoService.findAll(auto);
        List<Map> list = ServiceUtil.getList(autoList, this::toView);
        autoModel.setList(list);

        model.addAttribute("auto", autoModel);
        return "admin/auto.list";
    }

    public List getList() {
        List<Auto> autoList = autoService.list();
        List<Map> list = ServiceUtil.getList(autoList, this::toView);
        return  list;
    }

    public List getHeaders() {
        return HeaderView.builder()
                .add("marca", "auto.marca")
                .add("model", "auto.model")
                .add("categorieAuto", "-")
                .add("tipAuto", "-")
                .add("stareMatric", "Cilindree")
                .add("nrMatric", "Cilindree")
                .add("serieCiv", "Cilindree")
                .add("serieSasiu", "Cilindree")
                .add("putere", "Cilindree")
                .add("cilindree", "Cilindree")
                .add("masaMax", "Cilindree")
                .add("nrLocuri", "Cilindree")
                .add("anFabricatie", "auto.anFabricatie")
                .add("combustibil", "Cilindree")
                .add("utilizare", "Cilindree")
                .build();
    }


    //getter is needed also
    public Long getId (){
        return getModel().getId();
    }

    //called on loading
    public void setId (Long s){
        model.setId(s);
        Auto auto = autoService.load(model.getId());
        model = toModel(auto);
    }

    public AutoModel getModel() {
        return model;
    }


    @PostMapping(value = "")
    public String save() {
        Auto auto = buildDomain(model);
        autoService.save(auto);
        return redirect("list");
    }

    private String redirect(String url) {
        return url + "?faces-redirect=true";
    }

/*
    @PostMapping(value = "/ajax", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public AutoModel ajax(Long categAutoId) {
        List<TextValueModel> tipAutoList = tipAutoService.listCombo(categAutoId);

        AutoModel model = new AutoModel();
        model.setTipAutoList(tipAutoList);
        return model;
    }
*/

    @Cacheable
    public List getMarcaList (){
        return marcaService.listCombo();
    }
    @Cacheable
    public List getCategorieAutoList (){
        return categorieAutoService.listCombo();
    }
    @Cacheable
    public List getTipAutoList (){
        return tipAutoService.listCombo();
    }
    @Cacheable
    public List getCombustibilList (){
        return combustibilService.listCombo();
    }
    @Cacheable
    public List getUtilizareList (){
        return utilizareTypeService.listCombo();
    }
    @Cacheable
    public List getStareMatricList (){
        return stareMatricService.listCombo();
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
