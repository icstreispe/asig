package ro.x13.asig.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.x13.asig.db.dao.domain.Persoana;
import ro.x13.asig.db.dao.domain.Tara;
import ro.x13.asig.db.service.PersoanaService;
import ro.x13.asig.db.service.TaraService;
import ro.x13.asig.view.model.PersoanaModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class PersoanaResource {

    @Autowired
    private TaraService taraService;
    @Autowired
    private PersoanaService persoanaService;


    @GetMapping(value="/persoana")
    public String add(PersoanaModel persoanaModel, Model model) {
        persoanaModel.setPersoanaList(getList());

        getCombos(model, persoanaModel);

        model.addAttribute("persoana", persoanaModel);
        return "persoana";
    }



    @GetMapping(value="/persoana/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Persoana persoana = persoanaService.load(id);
        PersoanaModel persoanaModel = toModel(persoana);
        persoanaModel.setPersoanaList(getList());

        getCombos(model, persoanaModel);

        model.addAttribute("persoana", persoanaModel);
        return "persoana";
    }


    @PostMapping(value="/persoana")
    public String save(PersoanaModel persoanaModel) {
        Persoana persoana = buildDomain(persoanaModel);
        persoanaService.save(persoana);
        return "redirect:/persoana";
    }

    private void getCombos(Model model, PersoanaModel persoanaModel) {
        persoanaModel.setNationalitateList(taraService.listCombo());
        persoanaModel.setCetatenieList(taraService.listCombo());

        model.addAttribute("nationalitateList", persoanaModel.getNationalitateList());
        model.addAttribute("cetatenieList", persoanaModel.getCetatenieList());
    }

    private List<Map> getList() {
        List<Persoana> list = persoanaService.list();
        return StreamSupport.stream(list.spliterator(), false)
                .map(a -> toView(a))       // pt list(lista()): Persoana::toMap
                .collect(Collectors.toList());
    }

    private Persoana buildDomain(PersoanaModel persoanaModel) {
        Tara nationalitate = taraService.get(persoanaModel.getNationalitate());
        Tara cetatenie = taraService.get(persoanaModel.getCetatenie());
        return Persoana.builder()
                .id(persoanaModel.getId())
                .cnp(persoanaModel.getCnp())
                .cetatenie(cetatenie)
                .nationalitate(nationalitate)
                .nume(persoanaModel.getNume())
                .prenume(persoanaModel.getPrenume())
                .ciNumar(persoanaModel.getCiNumar())
                .ciSerie(persoanaModel.getCiSerie())
                .anPermis(persoanaModel.getAnPermis())
                .nrCopii(persoanaModel.getNrCopii())

                .email(persoanaModel.getEmail())
                .fax(persoanaModel.getFax())
                .telefon(persoanaModel.getTelefon())

                .politic(persoanaModel.getPolitic())
                .bugetar(persoanaModel.getBugetar())

                .build();
    }

    private Map toView(Persoana persoana) {
        Map m = new HashMap();
        m.put("id", persoana.getId());
        m.put("nume", persoana.getNume());
        m.put("prenume", persoana.getPrenume());
        m.put("cnp", persoana.getCnp());

        m.put("cetatenie", persoana.getCetatenie() == null ? null : persoana.getCetatenie().getName());
        m.put("nationalitate", persoana.getNationalitate() == null ? null : persoana.getNationalitate().getName());

        m.put("nrCopii", persoana.getNrCopii());
        m.put("anPermis", persoana.getAnPermis());
        m.put("ciNumar", persoana.getCiNumar());
        m.put("ciSerie", persoana.getCiSerie());

        m.put("bugetar", persoana.getBugetar());
        m.put("politic", persoana.getPolitic());

        m.put("email", persoana.getEmail());
        m.put("telefon", persoana.getTelefon());
        m.put("fax", persoana.getFax());
        return m;
    }

    private PersoanaModel toModel(Persoana persoana) {
        return PersoanaModel.builder()
                .id(persoana.getId())
                .cnp(persoana.getCnp())
                .nume(persoana.getNume())
                .prenume(persoana.getPrenume())

                .ciNumar(persoana.getCiNumar())
                .ciSerie(persoana.getCiSerie())
                .anPermis(persoana.getAnPermis())
                .nrCopii(persoana.getNrCopii())
                .telefon(persoana.getTelefon())
                .fax(persoana.getFax())
                .email(persoana.getEmail())

                .politic(persoana.getPolitic())
                .bugetar(persoana.getBugetar())

                .cetatenie(persoana.getCetatenie() == null ? null : persoana.getCetatenie().getId())
                .nationalitate(persoana.getNationalitate() == null ? null : persoana.getNationalitate().getId())
                .build();
    }




}
