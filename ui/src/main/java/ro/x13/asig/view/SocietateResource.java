package ro.x13.asig.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.dao.domain.Juridic;
import ro.x13.asig.db.dao.domain.org.Societate;
import ro.x13.asig.db.dao.domain.org.SocietateType;
import ro.x13.asig.db.service.JuridicService;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.service.SocietateService;
import ro.x13.asig.db.service.SocietateTypeService;
import ro.x13.asig.view.model.SocietateModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/societate")
public class SocietateResource {

    @Autowired
    private SocietateService societateService;
    @Autowired
    private SocietateTypeService societateTypeService;
    @Autowired
    private JuridicService juridicService;


    @GetMapping(value = "/list")
    public String list(Model model) {
        SocietateModel societateModel = new SocietateModel();
        List<Societate> societateList = societateService.list();
        List<Map> list = ServiceUtil.getList(societateList, this::toView);
        societateModel.setList(list);

        getCombos(model, societateModel);

        model.addAttribute("societate", societateModel);
        return "admin/societate.list";
    }

    @PostMapping(value = "/list")
    public String filter(Model model, SocietateModel societateModel) {
        Societate societate = buildDomain(societateModel);
        List<Societate> societateList = societateService.findAll(societate);
        List<Map> list = ServiceUtil.getList(societateList, this::toView);
        societateModel.setList(list);

        getCombos(model, societateModel);

        model.addAttribute("societate", societateModel);
        return "admin/societate.list";
    }


    @GetMapping(value = "/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Societate societate = societateService.get(id);
        SocietateModel societateModel = toModel(societate);

        getCombos(model, societateModel);

        model.addAttribute("societate", societateModel);
        return "admin/societate.form";
    }

    @GetMapping(value = "")
    public String add(SocietateModel societateModel, Model model) {

        getCombos(model, societateModel);

        model.addAttribute("societate", societateModel);
        return "admin/societate.form";
    }


    @PostMapping(value = "")
    public String save(SocietateModel societateModel) {
        Societate societate = buildDomain(societateModel);
        societateService.save(societate);
        return "redirect:/societate/list";
    }

    private void getCombos(Model model, SocietateModel societateModel) {
        societateModel.setJuridicList(juridicService.listCombo());
        model.addAttribute("tipStructuraList", societateModel.getJuridicList());
        societateModel.setTipList(societateTypeService.listCombo());
        model.addAttribute("tipStructuraList", societateModel.getTipList());
    }



    private Societate buildDomain(SocietateModel societateModel) {
        SocietateType tip = societateTypeService.get(societateModel.getTip());
        Juridic juridic = juridicService.get(societateModel.getJuridic());
        return Societate.builder()
                .id(societateModel.getId())
                .cif(societateModel.getCif())
                .name(societateModel.getNume())
                .adresa(societateModel.getAdresa())
                .regCom(societateModel.getRegCom())

                .fax(societateModel.getFax())
                .telefon(societateModel.getTelefon())
                .tip(tip)
                .juridic(juridic)

                .build();
    }

    private String toNull(String s) {
        return s == null ? null : (s.equals("") ? null : s);
    }

    private Map toView(Domain domain) {
        Societate societate = (Societate)  domain;
        Map m = new HashMap();
        m.put("id", societate.getId());
        m.put("nume", societate.getName());
        m.put("cif", societate.getCif());
        m.put("adresa", societate.getAdresa());
        m.put("regCom", societate.getRegCom());
        m.put("telefon", societate.getTelefon());
        m.put("fax", societate.getFax());
        m.put("juridic", societate.getJuridic() == null ? null : societate.getJuridic().getName());
        m.put("tip", societate.getTip() == null ? null : societate.getTip().getName());
        return m;
    }

    private SocietateModel toModel(Societate societate) {
        return SocietateModel.builder()
                .id(societate.getId())
                .cif(societate.getCif())
                .regCom(societate.getRegCom())
                .adresa(societate.getAdresa())
                .nume(societate.getName())

                .telefon(societate.getTelefon())
                .fax(societate.getFax())
                .tip(societate.getTip().getId())
                .juridic(societate.getJuridic().getId())

                .build();
    }
}
