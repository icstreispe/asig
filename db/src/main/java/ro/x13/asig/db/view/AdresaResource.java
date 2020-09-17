package ro.x13.asig.db.view;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.x13.asig.db.dao.domain.Adresa;
import ro.x13.asig.db.dao.domain.Judet;
import ro.x13.asig.db.dao.domain.Tara;
import ro.x13.asig.db.dao.domain.TipStrada;
import ro.x13.asig.db.filter.Loggable;
import ro.x13.asig.db.service.*;
import ro.x13.asig.db.view.model.AdresaModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@Slf4j
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
    @Loggable
    public String add(AdresaModel adresaModel, Model model) {
        adresaModel.setAdresaList(getList());
        getCombos(model, adresaModel);
        model.addAttribute("adresa", adresaModel);
        return "adresaForm";
    }

    @Loggable
    @GetMapping(value="/adresa/list")
    public String list(Model model) {
        AdresaModel adresaModel = new AdresaModel();
        adresaModel.setAdresaList(getList());
        model.addAttribute("adresa", adresaModel);
        return "adresaList";
    }

    @Loggable
    @GetMapping(value="/adresa/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Adresa adresa = adresaService.load(id);
        AdresaModel adresaModel = toModel(adresa);
        adresaModel.setAdresaList(getList());
        getCombos(model, adresaModel);
        model.addAttribute("adresa", adresaModel);
        return "adresaForm";
    }

    @Loggable
    @PostMapping(value="/adresa")
    public String save(AdresaModel adresaModel) {
        Adresa adresa = buildDomain(adresaModel);
        adresaService.save(adresa);
        return "redirect:/adresa/list";
    }

    @Loggable
    private List<Map> getList() {
        List<Adresa> list = adresaService.list();
        return StreamSupport.stream(list.spliterator(), false)
                .map(a -> toView(a))       // pt list(lista()): Adresa::toMap
                .collect(Collectors.toList());
    }

    @Loggable
    private void getCombos(Model model, AdresaModel adresaModel) {
        adresaModel.setJudetList(judetService.listCombo());
        adresaModel.setTaraList(taraService.listCombo());
        adresaModel.setTipStradaList(tipStradaService.listCombo());

        model.addAttribute("taraList", adresaModel.getTaraList());
        model.addAttribute("tipStradaList", adresaModel.getTipStradaList());
        model.addAttribute("judetList", adresaModel.getJudetList());
    }

    @Loggable
    private Adresa buildDomain(AdresaModel adresaModel) {
        Tara tara = taraService.get(adresaModel.getTara());
        Judet judet = judetService.get(adresaModel.getJudet());
        TipStrada tipStrada = tipStradaService.get(adresaModel.getTipStrada());
        return Adresa.builder()
                .id(adresaModel.getId())
                .scara(adresaModel.getScara())
                .tara(tara)
                .tipStrada(tipStrada)
                .strada(adresaModel.getStrada())
                .apartament(adresaModel.getApartament())
                .bloc(adresaModel.getBloc())
                .codPostal(adresaModel.getCodPostal())
                .etaj(adresaModel.getEtaj())
                .judet(judet)
                .numar(adresaModel.getNumar())
                .localitate(adresaModel.getLocalitate())
                .sublocalitate(adresaModel.getSublocalitate())
                .build();
    }

    @Loggable
    private Map toView(Adresa adresa) {
        Map m = new HashMap();
        m.put("id", adresa.getId());
        m.put("scara", adresa.getScara());
        m.put("tara", adresa.getTara() == null ? null : adresa.getTara().getName());
        m.put("tipStrada", adresa.getTipStrada() == null ? null : adresa.getTipStrada().getName());
        m.put("strada", adresa.getStrada());
        m.put("apartament", adresa.getApartament());
        m.put("bloc", adresa.getBloc());
        m.put("codPostal", adresa.getCodPostal());
        m.put("etaj", adresa.getEtaj());
        m.put("judet", adresa.getJudet() == null ? null : adresa.getJudet().getName());
        m.put("numar", adresa.getNumar());
        m.put("localitate", adresa.getLocalitate());
        m.put("sublocalitate", adresa.getSublocalitate());
        return m;
    }

    @Loggable
    private AdresaModel toModel(Adresa adresa) {
        return AdresaModel.builder()
                .id(adresa.getId())
                .scara(adresa.getScara())
                .tara(adresa.getTara() == null ? null : adresa.getTara().getId())
                .tipStrada(adresa.getTipStrada() == null ? null : adresa.getTipStrada().getId())
                .strada(adresa.getStrada())
                .apartament(adresa.getApartament())
                .bloc(adresa.getBloc())
                .codPostal(adresa.getCodPostal())
                .etaj(adresa.getEtaj())
                .judet(adresa.getJudet() == null ? null : adresa.getJudet().getId())
                .numar(adresa.getNumar())
                .localitate(adresa.getLocalitate())
                .sublocalitate(adresa.getSublocalitate()).build();
    }




}
