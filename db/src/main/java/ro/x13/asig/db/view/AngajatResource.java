package ro.x13.asig.db.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.x13.asig.db.dao.domain.org.Angajat;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.dao.domain.org.Societate;
import ro.x13.asig.db.filter.Loggable;
import ro.x13.asig.db.service.AngajatService;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.service.SocietateService;
import ro.x13.asig.db.view.model.AngajatModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping(value = "/angajat")
public class AngajatResource {

    @Autowired
    private AngajatService angajatService;
    @Autowired
    private SocietateService societateService;


    @GetMapping(value="")
    @Loggable
    public String add(AngajatModel angajatModel, Model model) {
        getCombos(model, angajatModel);
        model.addAttribute("angajat", angajatModel);
        return "admin/angajat.form";
    }

    @Loggable
    @GetMapping(value="/{id}")
    public String edit(Model model, @PathVariable("id") Long id, Authentication authentication) {
        Angajat angajat = angajatService.load(id);
        AngajatModel angajatModel = toModel(angajat);
        getCombos(model, angajatModel);
        model.addAttribute("angajat", angajatModel);
        return "admin/angajat.form";
    }

    @Loggable
    @GetMapping(value="/list")
    public String list(Model model) {
        AngajatModel angajatModel = new AngajatModel();
        List<Angajat> list = angajatService.list();
        angajatModel.setList(ServiceUtil.getList(list, this::toView));
        getCombos(model, angajatModel);

        model.addAttribute("angajat", angajatModel);
        return "admin/angajat.list";
    }

    @PostMapping(value = "/list")
    public String filter(Model model, AngajatModel angajatModel) {
        Angajat angajat = buildDomain(angajatModel);
        List<Angajat> list = angajatService.findAll(angajat);
        List<Map> angajatListMap = ServiceUtil.getList(list, this::toView);
        angajatModel.setList(angajatListMap);
        getCombos(model, angajatModel);

        model.addAttribute("angajat", angajatModel);
        return "admin/angajat.list";
    }


    @Loggable
    @PostMapping(value="")
    public String save(AngajatModel angajatModel) {
        Angajat angajat = buildDomain(angajatModel);
        angajatService.save(angajat);
        return "redirect:/angajat/list";
    }


    @Loggable
    private void getCombos(Model model, AngajatModel angajatModel) {
        angajatModel.setSocietateList(societateService.listCombo());
        model.addAttribute("societateList", angajatModel.getSocietateList());
    }

    @Loggable
    private Angajat buildDomain(AngajatModel angajatModel) {
        Angajat angajat = angajatService.load(angajatModel.getId());
        Societate societate = societateService.get(angajatModel.getSocietate());
        angajat.setId(angajatModel.getId());
        angajat.setCiNumar(angajatModel.getCiNumar());
        angajat.setCiSerie(angajatModel.getCiSerie());
        angajat.setUsername(angajatModel.getUsername());
        angajat.setSocietate(societate);
        angajat.setCnp(angajatModel.getCnp());
        angajat.setCod(angajatModel.getCod());
        angajat.setNume(angajatModel.getNume());
        angajat.setPrenume(angajatModel.getPrenume());
        angajat.setTelefon(angajatModel.getTelefon());
        angajat.setEmail(angajatModel.getEmail());
        return angajat;
    }

    @Loggable
    private Map toView(Domain domain) {
        Angajat angajat = (Angajat) domain;
        Map m = new HashMap();
        m.put("id", angajat.getId());
        m.put("cnp", angajat.getCnp());
        m.put("cod", angajat.getCod());
        m.put("username", angajat.getUsername());
        m.put("telefon", angajat.getTelefon());
        m.put("email", angajat.getEmail());
        m.put("nume", angajat.getNume());
        m.put("prenume", angajat.getPrenume());
        m.put("societate", angajat.getSocietate() == null ? null : angajat.getSocietate().getName());
        m.put("ciNumar", angajat.getCiNumar());
        m.put("ciSerie", angajat.getCiSerie());
        return m;
    }

    @Loggable
    private AngajatModel toModel(Angajat angajat) {
        return AngajatModel.builder()
                .id(angajat.getId())
                .cod(angajat.getCod())
                .cnp(angajat.getCnp())
                .ciNumar(angajat.getCiNumar())
                .ciSerie(angajat.getCiSerie())
                .telefon(angajat.getTelefon())
                .email(angajat.getEmail())
                .societate(angajat.getSocietate() == null ? null : angajat.getSocietate().getId())
                .nume(angajat.getNume())
                .prenume(angajat.getPrenume())
                .username(angajat.getUsername())
                .build();
    }




}
