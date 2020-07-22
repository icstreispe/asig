package ro.x13.asig.db.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.x13.asig.db.dao.domain.Asig;
import ro.x13.asig.db.dao.domain.Tara;
import ro.x13.asig.db.service.AsigService;
import ro.x13.asig.db.service.TaraService;
import ro.x13.asig.db.view.model.AsigModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class AsigResource {

    @Autowired
    private TaraService taraService;
    @Autowired
    private AsigService asigService;


    @GetMapping(value="/asig")
    public String add(AsigModel asigModel, Model model) {
        asigModel.setAsigList(getList());

        getCombos(model, asigModel);

        model.addAttribute("asig", asigModel);
        return "admin/asig";
    }



    @GetMapping(value="/asig/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Asig asig = asigService.load(id);
        AsigModel asigModel = toModel(asig);
        asigModel.setAsigList(getList());

        getCombos(model, asigModel);

        model.addAttribute("asig", asigModel);
        return "admin/asig";
    }


    @PostMapping(value="/asig")
    public String save(AsigModel asigModel) {
        Asig asig = buildAsig(asigModel);
        asigService.save(asig);
        return "redirect:/asig";
    }

    private void getCombos(Model model, AsigModel asigModel) {
    }

    private List<Map> getList() {
        List<Asig> list = asigService.list();
        return StreamSupport.stream(list.spliterator(), false)
                .map(a -> toView(a))       // pt list(lista()): Asig::toMap
                .collect(Collectors.toList());
    }

    private Asig buildAsig(AsigModel asigModel) {
        return Asig.builder()
                .id(asigModel.getId())
                .cui(asigModel.getCui())
                .nume(asigModel.getNume())

                .fax(asigModel.getFax())
                .telefon(asigModel.getTelefon())


                .build();
    }

    private Map toView(Asig asig) {
        Map m = new HashMap();
        m.put("id", asig.getId());
        m.put("nume", asig.getNume());
        m.put("cui", asig.getCui());

        m.put("telefon", asig.getTelefon());
        m.put("fax", asig.getFax());
        return m;
    }

    private AsigModel toModel(Asig asig) {
        return AsigModel.builder()
                .id(asig.getId())
                .cui(asig.getCui())
                .nume(asig.getNume())

                .telefon(asig.getTelefon())
                .fax(asig.getFax())

                .build();
    }




}
