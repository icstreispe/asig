package ro.x13.asig.db.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.x13.asig.db.dao.domain.AsigType;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.dao.domain.Societate;
import ro.x13.asig.db.dao.domain.Produs;
import ro.x13.asig.db.service.AsigTypeService;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.service.SocietateService;
import ro.x13.asig.db.service.ProdusService;
import ro.x13.asig.db.view.model.ProdusModel;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = "/produs")
public class ProdusResource {

    @Autowired
    private ProdusService produsService;
    @Autowired
    private SocietateService societateService;
    @Autowired
    private AsigTypeService asigTypeService;


    @GetMapping(value = "/list")
    public String list(Model model) {
        ProdusModel produsModel = new ProdusModel();
        List<Produs> list = produsService.list();
        produsModel.setList(ServiceUtil.getList(list, this::toView));

        getCombos(model, produsModel);

        model.addAttribute("produs", produsModel);
        return "admin/produs.list";
    }

    @PostMapping(value = "/list")
    public String filter(Model model, ProdusModel produsModel) {
        Produs produs = buildDomain(produsModel);
        List<Produs> produsList = produsService.list(produs);
        List<Map> produsListMap = ServiceUtil.getList(produsList, this::toView);
        produsModel.setList(produsListMap);
        getCombos(model, produsModel);

        model.addAttribute("produs", produsModel);
        return "admin/produs.list";
    }

    @GetMapping(value = "")
    public String add(ProdusModel produsModel, Model model) {

        getCombos(model, produsModel);

        model.addAttribute("produs", produsModel);
        return "admin/produs.form";
    }


    @GetMapping(value = "/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Produs produs = produsService.get(id);
        ProdusModel produsModel = toModel(produs);

        getCombos(model, produsModel);

        model.addAttribute("produs", produsModel);
        return "admin/produs.form";
    }


    @PostMapping(value = "")
    public String save(ProdusModel produsModel) {
        Produs produs = buildDomain(produsModel);
        produsService.save(produs);
        return "redirect:/produs/list";
    }

    @PostMapping(value = "/ajax", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProdusModel ajax(Long id) {
        ProdusModel model = new ProdusModel();
        model.setName("");
        model.setSocietateList(societateService.listCombo());
        return model;
    }

    private void getCombos(Model model, ProdusModel produsModel) {
        produsModel.setSocietateList(societateService.listCombo());
        produsModel.setAsigTypeList(asigTypeService.listCombo());
    }



    private Produs buildDomain(ProdusModel produsModel) {
        Societate societate = societateService.get(produsModel.getSocietate());
        AsigType asigType = asigTypeService.get(produsModel.getAsigType());
        return Produs.builder()
                .id(produsModel.getId())
                .name(produsModel.getName())
                .societate(societate)
                .asigType(asigType)
                .build();
    }

    private String toNull(String s) {
        return s == null ? null : (s.equals("") ? null : s);
    }

    private Map toView(Domain domain) {
        Produs produs = (Produs) domain;
        Map m = new HashMap();
        m.put("id", produs.getId());
        m.put("name", produs.getName());
        m.put("societate", produs.getSocietate() == null ? null : produs.getSocietate().getName());
        m.put("asigType", produs.getAsigType() == null ? null : produs.getAsigType().getName());
        return m;
    }

    private ProdusModel toModel(Produs produs) {
        return ProdusModel.builder()
                .id(produs.getId())
                .name(produs.getName())
                .societate(produs.getSocietate().getId())
                .asigType(produs.getAsigType().getId())

                .build();
    }
}
