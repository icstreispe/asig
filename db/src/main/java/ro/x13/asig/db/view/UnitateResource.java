package ro.x13.asig.db.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.dao.domain.org.Societate;
import ro.x13.asig.db.dao.domain.org.Unitate;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.service.SocietateService;
import ro.x13.asig.db.service.UnitateService;
import ro.x13.asig.db.view.model.UnitateModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/unitate")
public class UnitateResource {

    @Autowired
    private UnitateService unitateService;
    @Autowired
    private SocietateService societateService;

    @GetMapping(value = "/list")
    public String list(Model model) {
        UnitateModel unitateModel = new UnitateModel();
        List<Unitate> list = unitateService.list();
        unitateModel.setList(ServiceUtil.getList(list, this::toView));

        getCombos(model, unitateModel);

        model.addAttribute("unitate", unitateModel);
        return "admin/unitate.list";
    }

    @PostMapping(value = "/list")
    public String filter(Model model, UnitateModel unitateModel) {
        Unitate unitate = buildDomain(unitateModel);
        List<Unitate> unitateList = unitateService.list(unitate);
        List<Map> unitateListMap = ServiceUtil.getList(unitateList, this::toView);
        unitateModel.setList(unitateListMap);
        getCombos(model, unitateModel);

        model.addAttribute("unitate", unitateModel);
        return "admin/unitate.list";
    }

    @GetMapping(value = "")
    public String add(UnitateModel unitateModel, Model model) {

        getCombos(model, unitateModel);

        model.addAttribute("unitate", unitateModel);
        return "admin/unitate.form";
    }


    @GetMapping(value = "/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Unitate unitate = unitateService.get(id);
        UnitateModel unitateModel = toModel(unitate);

        getCombos(model, unitateModel);

        model.addAttribute("unitate", unitateModel);
        return "admin/unitate.form";
    }


    @PostMapping(value = "")
    public String save(UnitateModel unitateModel) {
        Unitate unitate = buildDomain(unitateModel);
        unitateService.save(unitate);
        return "redirect:/unitate/list";
    }

    private void getCombos(Model model, UnitateModel unitateModel) {
        unitateModel.setSocietateList(societateService.listCombo());
        model.addAttribute("societateList", unitateModel.getSocietateList());
    }



    private Unitate buildDomain(UnitateModel unitateModel) {
        Societate societate = societateService.get(unitateModel.getSocietate());
        return Unitate.builder()
                .id(unitateModel.getId())
                .name(unitateModel.getName())
                .adresa(unitateModel.getAdresa())
                .fax(unitateModel.getFax())
                .telefon(unitateModel.getTelefon())
                .societate(societate)
                .build();
    }

    private String toNull(String s) {
        return s == null ? null : (s.equals("") ? null : s);
    }

    private Map toView(Domain domain) {
        Unitate unitate = (Unitate) domain;
        Map m = new HashMap();
        m.put("id", unitate.getId());
        m.put("name", unitate.getName());
        m.put("adresa", unitate.getAdresa());
        m.put("fax", unitate.getFax());
        m.put("telefon", unitate.getTelefon());
        m.put("societate", unitate.getSocietate() == null ? null : unitate.getSocietate().getName());
        return m;
    }

    private UnitateModel toModel(Unitate unitate) {
        return UnitateModel.builder()
                .id(unitate.getId())
                .adresa(unitate.getAdresa())
                .name(unitate.getName())

                .telefon(unitate.getTelefon())
                .fax(unitate.getFax())
                .societate(unitate.getSocietate().getId())

                .build();
    }
}
