package ro.x13.asig.db.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.x13.asig.db.dao.domain.Asig;
import ro.x13.asig.db.dao.domain.Juridic;
import ro.x13.asig.db.dao.domain.SocietateType;
import ro.x13.asig.db.service.AsigService;
import ro.x13.asig.db.service.JuridicService;
import ro.x13.asig.db.service.SocietateTypeService;
import ro.x13.asig.db.view.model.AsigModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(value = "/asig")
public class AsigResource {

    @Autowired
    private AsigService asigService;
    @Autowired
    private SocietateTypeService societateTypeService;
    @Autowired
    private JuridicService juridicService;


    @GetMapping(value = "")
    public String add(AsigModel asigModel, Model model) {
        asigModel.setAsigList(getList());

        getCombos(model, asigModel);

        model.addAttribute("asig", asigModel);
        return "admin/asig.form";
    }


    @GetMapping(value = "/list")
    public String edit(Model model) {
        AsigModel asigModel = new AsigModel();
        asigModel.setAsigList(getList());

        model.addAttribute("asig", asigModel);
        return "admin/asig.list";
    }


    @GetMapping(value = "/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Asig asig = asigService.get(id);
        AsigModel asigModel = toModel(asig);
        asigModel.setAsigList(getList());

        getCombos(model, asigModel);

        model.addAttribute("asig", asigModel);
        return "admin/asig.form";
    }

    @PostMapping(value = "/filter")
    public String filter(AsigModel asigModel) {
        //Asig asig = buildAsig(asigModel);
        Juridic j = juridicService.get(asigModel.getJuridic());
        List<Asig> asigList = asigService.findAllByJuridic(j);
        asigModel.setAsigList(asigList);
        return "admin/asig";
    }


    @PostMapping(value = "")
    public String save(AsigModel asigModel) {
        Asig asig = buildAsig(asigModel);
        asigService.save(asig);
        return "redirect:/asig/list";
    }

    private void getCombos(Model model, AsigModel asigModel) {
        asigModel.setJuridicList(juridicService.listCombo());
        model.addAttribute("tipStructuraList", asigModel.getJuridicList());
        asigModel.setTipList(societateTypeService.listCombo());
        model.addAttribute("tipStructuraList", asigModel.getTipList());
    }

    private List<Map> getList() {
        List<Asig> list = asigService.list();
        return StreamSupport.stream(list.spliterator(), false)
                .map(a -> toView(a))       // pt list(lista()): Asig::toMap
                .collect(Collectors.toList());
    }

    private Asig buildAsig(AsigModel asigModel) {
        SocietateType tip = societateTypeService.get(asigModel.getTip());
        Juridic juridic = juridicService.get(asigModel.getJuridic());
        return Asig.builder()
                .id(asigModel.getId())
                .cif(asigModel.getCif())
                .name(asigModel.getNume())
                .adresa(asigModel.getAdresa())
                .regCom(asigModel.getRegCom())

                .fax(asigModel.getFax())
                .telefon(asigModel.getTelefon())
                .tip(tip)
                .juridic(juridic)

                .build();
    }

    private Map toView(Asig asig) {
        Map m = new HashMap();
        m.put("id", asig.getId());
        m.put("nume", asig.getName());
        m.put("cif", asig.getCif());
        m.put("adresa", asig.getAdresa());
        m.put("regCom", asig.getRegCom());
        m.put("telefon", asig.getTelefon());
        m.put("fax", asig.getFax());
        m.put("juridic", asig.getJuridic() == null ? null : asig.getJuridic().getName());
        m.put("tip", asig.getTip() == null ? null : asig.getTip().getName());
        return m;
    }

    private AsigModel toModel(Asig asig) {
        return AsigModel.builder()
                .id(asig.getId())
                .cif(asig.getCif())
                .regCom(asig.getRegCom())
                .adresa(asig.getAdresa())
                .nume(asig.getName())

                .telefon(asig.getTelefon())
                .fax(asig.getFax())
                .tip(asig.getTip().getId())
                .juridic(asig.getJuridic().getId())

                .build();
    }
}
