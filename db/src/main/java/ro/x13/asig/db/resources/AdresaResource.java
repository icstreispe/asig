package ro.x13.asig.db.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.x13.asig.db.dao.domain.TipStrada;
import ro.x13.asig.db.service.*;
import ro.x13.asig.db.service.model.AdresaModel;

@Controller
public class AdresaResource {

    @Autowired
    private TaraService taraService;
    @Autowired
    private JudetService judetService;
    @Autowired
    private AdresaService adresaService;
    @Autowired
    private TipStradaService tipStradaService;


    @GetMapping(value="/adresa")
    public String newAdresa(AdresaModel adresa, Model model) {
        adresa.setAdresaList(adresaService.list());
        adresa.setJudetList(judetService.listCombo());
        adresa.setTaraList(taraService.listCombo());
        adresa.setTipStradaList(tipStradaService.listCombo());
        model.addAttribute("adresa", adresa);
        return "index";
    }

    @GetMapping(value="/adresa/{id}")
    public String editAdresa(Model model, @PathVariable("id") Long id) {
        AdresaModel adresa = adresaService.load(id);
        adresa.setAdresaList(adresaService.list());
        adresa.setJudetList(judetService.listCombo());
        adresa.setTaraList(taraService.listCombo());
        adresa.setTipStradaList(tipStradaService.listCombo());
        model.addAttribute("adresa", adresa);
        return "index";
    }


    @PostMapping(value="/adresa")
    public String saveAdresa(AdresaModel adresa) {
        adresaService.save(adresa);
        return "redirect:/adresa";
    }





}
