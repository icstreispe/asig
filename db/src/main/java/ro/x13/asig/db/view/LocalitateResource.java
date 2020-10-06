package ro.x13.asig.db.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.dao.domain.Judet;
import ro.x13.asig.db.dao.domain.Localitate;
import ro.x13.asig.db.service.*;
import ro.x13.asig.db.view.model.LocalitateModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequiredArgsConstructor
@RequestMapping("/localitate")
public class LocalitateResource {

    private final LocalitateService localitateService;
    private final JudetService judetService;



    @GetMapping(value = "/list")
    public String list(Model model) {
        LocalitateModel localitateModel = new LocalitateModel();
        List<Localitate> localitateList = localitateService.list();
        List<Map> list = ServiceUtil.getList(localitateList, this::toView);
        localitateModel.setList(list);

        getCombos(model, localitateModel);

        model.addAttribute("localitate", localitateModel);
        return "admin/localitate.list";
    }

    @PostMapping(value = "/list")
    public String filter(Model model, LocalitateModel localitateModel) {
        Localitate localitate = buildDomain(localitateModel);
        List<Localitate> localitateList = localitateService.findAll(localitate);
        List<Map> list = ServiceUtil.getList(localitateList, this::toView);
        localitateModel.setList(list);

        getCombos(model, localitateModel);

        model.addAttribute("localitate", localitateModel);
        return "admin/localitate.list";
    }


    @GetMapping(value="")
    public String add(LocalitateModel localitateModel, Model model) {
        localitateModel.setList(getList());

        getCombos(model, localitateModel);

        model.addAttribute("localitate", localitateModel);
        return "admin/localitate.form";
    }



    @GetMapping(value="/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Localitate localitate = localitateService.get(id);
        LocalitateModel localitateModel = toModel(localitate);

        getCombos(model, localitateModel);

        model.addAttribute("localitate", localitateModel);
        return "admin/localitate.form";
    }

    @PostMapping(value = "/ajax", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public LocalitateModel ajx(Long id) {
/*  TODO
        Societate societate = societateService.get(id);
        Judet p = new Judet();
        p.setSocietate(societate);
        List<TextValueModel> judetList = judetService.listCombo(p);
*/
        LocalitateModel model = new LocalitateModel();
        //model.setJudetList(judetList);
        return model;
    }


    @PostMapping(value="")
    public String save(LocalitateModel localitateModel) {
        Localitate localitate = buildDomain(localitateModel);
        localitateService.save(localitate);
        return "redirect:/localitate.list";
    }

    private void getCombos(Model model, LocalitateModel localitateModel) {
        localitateModel.setJudetList(judetService.listCombo());
    }

    private List<Map> getList() {
        List<Localitate> list = localitateService.list();
        return ServiceUtil.getList(list, this::toView);
    }

    private Localitate buildDomain(LocalitateModel localitateModel) {
        Judet judet = judetService.get(localitateModel.getJudet());
        return Localitate.builder()
                .id(localitateModel.getId())
                .name(localitateModel.getName())
                .latitude(localitateModel.getLatitude())
                .longitude(localitateModel.getLongitude())
                .judet(judet)
                .population(localitateModel.getPopulation())
                .firms(localitateModel.getFirms())
                .apartenenta(localitateModel.getApartenenta())
                .build();
    }

    private Map toView(Domain domain) {
        Localitate localitate = (Localitate) domain;
        Map m = new HashMap();
        m.put("id", localitate.getId());
        m.put("name", localitate.getName());
        m.put("population", localitate.getPopulation());
        m.put("firms", localitate.getFirms());
        m.put("judet", localitate.getJudet() == null ? null : localitate.getJudet().getName());
        m.put("longitude", localitate.getLongitude() == null ? null : localitate.getLongitude());
        m.put("latitude", localitate.getLatitude() == null ? null : localitate.getLatitude());
        m.put("apartenenta", localitate.getApartenenta());

        return m;
    }

    private LocalitateModel toModel(Localitate localitate) {
        return LocalitateModel.builder()
                .id(localitate.getId())
                .name(localitate.getName())
                .firms(localitate.getFirms())
                .population(localitate.getPopulation())
                .longitude(localitate.getLongitude())
                .latitude(localitate.getLatitude())
                .apartenenta(localitate.getApartenenta())
                .judet(localitate.getJudet() == null ? null : localitate.getJudet().getId())
                .build();
    }
}
