package ro.x13.asig.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.x13.asig.db.dao.domain.*;
import ro.x13.asig.db.service.*;
import ro.x13.asig.view.model.ImobilModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/imobil")
public class ImobilResource {

    @Autowired
    private ImobilService imobilService;
    @Autowired
    private ConstructieService constructieService;
    @Autowired
    private MediuService mediuService;
    @Autowired
    private StructuraTypeService structuraTypeService;


    @GetMapping(value = "/list")
    public String list(Model model) {
        ImobilModel imobilModel = new ImobilModel();
        List<Imobil> imobilList = imobilService.list();
        List<Map> list = ServiceUtil.getList(imobilList, this::toView);
        imobilModel.setList(list);

        getCombos(model, imobilModel);

        model.addAttribute("imobil", imobilModel);
        return "imobil.list";
    }

    @PostMapping(value = "/list")
    public String filter(Model model, ImobilModel imobilModel) {
        Imobil imobil = buildDomain(imobilModel);
        List<Imobil> imobilList = imobilService.list(imobil);
        List<Map> list = ServiceUtil.getList(imobilList, this::toView);
        imobilModel.setList(list);
        getCombos(model, imobilModel);

        model.addAttribute("imobil", imobilModel);
        return "imobi.lList";
    }

    @GetMapping(value="")
    public String add(ImobilModel imobilModel, Model model) {
        List<Imobil> list = imobilService.list();
        List<Map> listMap = ServiceUtil.getList(list, this::toView);
        imobilModel.setList(listMap);

        getCombos(model, imobilModel);

        model.addAttribute("imobil", imobilModel);
        return "imobil.form";
    }



    @GetMapping(value="/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Imobil imobil = imobilService.load(id);
        ImobilModel imobilModel = toModel(imobil);

        getCombos(model, imobilModel);

        model.addAttribute("imobil", imobilModel);
        return "imobil.form";
    }


    @PostMapping(value="")
    public String save(ImobilModel imobilModel) {
        Imobil imobil = buildDomain(imobilModel);
        imobilService.save(imobil);
        return "redirect:/imobil/list";
    }


    private void getCombos(Model model, ImobilModel imobilModel) {
        imobilModel.setConstructieList(constructieService.listCombo());
        imobilModel.setMediuList(mediuService.listCombo());
        imobilModel.setStructuraTypeList(structuraTypeService.listCombo());
        imobilModel.setStructuraTypeList(structuraTypeService.listCombo());
    }


    private Imobil buildDomain(ImobilModel imobilModel) {
        ConstructieType constructieType = constructieService.get(imobilModel.getConstructie());
        MediuType mediu = mediuService.get(imobilModel.getMediu());
        StructuraType structuraType = structuraTypeService.get(imobilModel.getStructuraType());
        return Imobil.builder()
                .id(imobilModel.getId())
                .anConstructie(imobilModel.getAnConstructie())
                .constructie(constructieType)
                .mediu(mediu)
                .suprafata(imobilModel.getSuprafata())
                .vecinatate(imobilModel.getVecinatate())
                .nrCamere(imobilModel.getNrCamere())
                .nrCladiri(imobilModel.getNrCladiri())
                .nrEtaje(imobilModel.getNrEtaje())
                .structuraType(structuraType)
                .mentiuni(imobilModel.getMentiuni())
                .build();
    }

    private Map toView(Domain domain) {
        Imobil imobil = (Imobil) domain;
        Map m = new HashMap();
        m.put("id", imobil.getId());
        m.put("constructie", imobil.getConstructie() == null ? null : imobil.getConstructie().getName());
        m.put("structuraType", imobil.getStructuraType() == null ? null : imobil.getStructuraType().getName());
        m.put("mediu", imobil.getMediu() == null ? null : imobil.getMediu().getName());
        m.put("anConstructie", imobil.getAnConstructie());
        m.put("suprafata", imobil.getSuprafata());
        m.put("nrCamere", imobil.getNrCamere());
        m.put("nrCladiri", imobil.getNrCladiri());
        m.put("nrEtaje", imobil.getNrEtaje());
        m.put("mentiuni", imobil.getMentiuni());
        m.put("vecinatate", imobil.getVecinatate());

        return m;
    }

    private ImobilModel toModel(Imobil imobil) {
        return ImobilModel.builder()
                .id(imobil.getId())
                .anConstructie(imobil.getAnConstructie())
                .constructie(imobil.getConstructie() == null ? null : imobil.getConstructie().getId())
                .mediu(imobil.getMediu() == null ? null : imobil.getMediu().getId())
                .structuraType(imobil.getStructuraType() == null ? null : imobil.getStructuraType().getId())
                .anConstructie(imobil.getAnConstructie())
                .mentiuni(imobil.getMentiuni())
                .nrCamere(imobil.getNrCamere())
                .nrCladiri(imobil.getNrCladiri())
                .nrEtaje(imobil.getNrEtaje())
                .suprafata(imobil.getSuprafata())
                .vecinatate(imobil.getVecinatate())
                .build();
    }
}
