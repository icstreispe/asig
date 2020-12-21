package ro.x13.asig.db.view.biz;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ro.x13.asig.db.dao.domain.Auto;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.filter.RequestScopedController;
import ro.x13.asig.db.service.*;
import ro.x13.asig.db.view.ListView;
import ro.x13.asig.db.view.jsf.HeaderView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.x13.asig.db.ViewUtil.redirect;

@RequestScopedController("autoList")
@RequiredArgsConstructor
@Getter
@Setter
public class AutoListView implements ListView {

    private int page;
    //private int totalPage;
    private int rowPerPage = 5;

    private final AutoService autoService;
    private final CategorieAutoService categorieAutoService;
    private final TipAutoService tipAutoService;
    private final MarcaService marcaService;
    private final CombustibilService combustibilService;
    private final UtilizareTypeService utilizareTypeService;
    private final StareMatricService stareMatricService;

/*
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
*/

    public void pagePrevious (){
            page--;
    }

    public void pageNext (){
        page++;
    }

    @Override
    public String getName(){
        return "auto.";
    }

    @Override
    public List getList() {
        Iterable<Auto> autoList = autoService.listAll(new Auto(), page, rowPerPage);
        List<Map> list = ServiceUtil.getList(autoList, this::toView);
        return  list;
    }

    @Override
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


    @Override
    public String del(Long id) {
        autoService.del(id);
        return redirect("list");
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

}
