package ro.x13.asig.view.model.auto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.x13.asig.db.dao.biz.*;
import ro.x13.asig.db.dao.domain.Auto;
import ro.x13.asig.db.service.*;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ro.x13.asig.util.ViewUtil.redirect;
import static ro.x13.asig.view.model.auto.AutoListResource.AUTO_URL;

@Controller
@RequiredArgsConstructor
@RequestMapping(AUTO_URL + "/form")
public class AutoFormResource {

    private final AutoService autoService;
    private final CategorieAutoService categorieAutoService;
    private final TipAutoService tipAutoService;
    private final MarcaService marcaService;
    private final CombustibilService combustibilService;
    private final UtilizareTypeService utilizareTypeService;
    private final StareMatricService stareMatricService;


    @GetMapping(value = {"", "/{id}"})
    public String load(Model model, AutoFormModel autoModel) {
        Auto auto = autoService.load(autoModel.getId());

        toModel(autoModel, auto);
        getCombos(autoModel);

        model.addAttribute(autoModel.getModelName(), autoModel);
        return "admin/auto.form";
    }


    @PostMapping(value = "")
    public String save(AutoFormModel autoModel) {
        Auto auto = getDomain(autoModel);
        autoService.save(auto);
        return redirect(AUTO_URL + "/list");
    }


    @PostMapping(value = "/ajax", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public AutoFormModel ajax(Long categorieAuto) {   //matches id/name of the field!!!
        List<TextValueModel> tipAutoList = tipAutoService.listCombo(categorieAuto);

        AutoFormModel model = new AutoFormModel();
        model.getTipAuto().setList(tipAutoList);
        return model;
    }

    private void getCombos(AutoFormModel autoModel) {
        autoModel.getCategorieAuto().setList(categorieAutoService.listCombo());
        autoModel.getMarca().setList(marcaService.listCombo());
        autoModel.getTipAuto().setList(tipAutoService.listCombo());
        autoModel.getCombustibil().setList(combustibilService.listCombo());
        autoModel.getUtilizare().setList(utilizareTypeService.listCombo());
        autoModel.getStareMatric().setList(stareMatricService.listCombo());
    }

    private Auto getDomain(AutoFormModel autoModel) {
        CategorieAuto categorieAuto = categorieAutoService.get(autoModel.getCategorieAuto().getValue());
        TipAuto tipAuto = tipAutoService.get(autoModel.getTipAuto().getValue());
        Combustibil combustibil = combustibilService.get(autoModel.getCombustibil().getValue());
        UtilizareType utilizare = utilizareTypeService.get(autoModel.getUtilizare().getValue());
        Marca marca = marcaService.get(autoModel.getMarca().getValue());
        StareMatric stareMatric = stareMatricService.get(autoModel.getStareMatric().getValue());
        return Auto.builder()
                .id(autoModel.getId())
                .anFabricatie(autoModel.getAnFabricatie().getValue())
                .cilindree(autoModel.getCilindree().getValue())
                .categorieAuto(categorieAuto)
                .tipAuto(tipAuto)
                .combustibil(combustibil)
                .utilizare(utilizare)
                .marca(marca)
                .stareMatric(stareMatric)
                .masaMax(autoModel.getMasaMax().getValue())
                .model(autoModel.getModel().getValue())
                .nrLocuri(autoModel.getNrLocuri().getValue())
                .nrMatric(autoModel.getNrMatric().getValue())
                .serieCiv(autoModel.getSerieCiv().getValue())
                .serieSasiu(autoModel.getSerieSasiu().getValue())
                .putere(autoModel.getPutere().getValue())
                .build();
    }


    private void toModel(AutoFormModel model, Auto auto) {
        model.setId(auto.getId());
        model.getAnFabricatie().setValue(auto.getAnFabricatie());
        model.getCilindree().setValue(auto.getCilindree());
        model.getCategorieAuto().setValue(auto.getCategorieAuto() == null ? null : auto.getCategorieAuto().getId());
        model.getTipAuto().setValue(auto.getTipAuto() == null ? null : auto.getTipAuto().getId());
        model.getMarca().setValue(auto.getMarca() == null ? null : auto.getMarca().getId());
        model.getStareMatric().setValue(auto.getStareMatric() == null ? null : auto.getStareMatric().getId());
        model.getUtilizare().setValue(auto.getUtilizare() == null ? null : auto.getUtilizare().getId());
        model.getCombustibil().setValue(auto.getCombustibil() == null ? null : auto.getCombustibil().getId());
        model.getMasaMax().setValue(auto.getMasaMax());
        model.getModel().setValue(auto.getModel());
        model.getNrLocuri().setValue(auto.getNrLocuri());
        model.getNrMatric().setValue(auto.getNrMatric());
        model.getSerieCiv().setValue(auto.getSerieCiv());
        model.getSerieSasiu().setValue(auto.getSerieSasiu());
        model.getPutere().setValue(auto.getPutere());
    }
}
