package ro.x13.asig.db.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.x13.asig.db.dao.domain.Angajat;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.dao.domain.org.Password;
import ro.x13.asig.db.dao.domain.org.Societate;
import ro.x13.asig.db.filter.Loggable;
import ro.x13.asig.db.service.AngajatService;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.service.SocietateService;
import ro.x13.asig.db.service.org.PasswordService;
import ro.x13.asig.db.view.model.AngajatModel;
import ro.x13.asig.db.view.model.PasswordModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping(value = "/password")
public class PasswordResource {

    @Autowired
    private PasswordService passwordService;
    @Autowired
    private AngajatService angajatService;
    @Autowired
    private SocietateService societateService;


    @Loggable
    @GetMapping(value="/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Angajat angajat = angajatService.load(id);
        PasswordModel passwordModel = toModel(angajat);
        getCombos(model, passwordModel);
        model.addAttribute("password", passwordModel);
        return "admin/password.form";
    }

    @Loggable
    private void getCombos(Model model, PasswordModel angajatModel) {
        angajatModel.setSocietateList(societateService.listCombo());
        model.addAttribute("societateList", angajatModel.getSocietateList());
    }

    @Loggable
    @PostMapping(value="")
    public String save(PasswordModel angajatModel) {
        Password password = buildDomain(angajatModel);
        passwordService.save(password); //TODO save moved to angajatService

        Angajat angajat = angajatService.load(angajatModel.getId());
        angajat.setPassword(password);
        angajatService.save(angajat);

        return "redirect:/angajat/list";

    }

    private Password buildDomain(PasswordModel angajatModel) {
        Angajat angajat = angajatService.load(angajatModel.getId());
        Password password = Password.builder()
                .angajat(angajat)
                .password(angajatModel.getPassword())
                .build();
         return password;
    }


    @Loggable
    private PasswordModel toModel(Angajat angajat) {
        return PasswordModel.builder()
                .id(angajat.getId())
                .cod(angajat.getCod())
                .cnp(angajat.getCnp())
                .societate(angajat.getSocietate() == null ? null : angajat.getSocietate().getId())
                .nume(angajat.getNume())
                .prenume(angajat.getPrenume())
                .password(angajat.getPassword() == null ? null : angajat.getPassword().getPassword())
                .build();
    }
}
