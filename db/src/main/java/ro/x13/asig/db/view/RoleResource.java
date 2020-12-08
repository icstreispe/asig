package ro.x13.asig.db.view;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ro.x13.asig.db.dao.domain.Persoana;
import ro.x13.asig.db.dao.domain.org.Angajat;
import ro.x13.asig.db.dao.domain.org.Rol;
import ro.x13.asig.db.dao.domain.org.Unitate;
import ro.x13.asig.db.dao.domain.org.User;
import ro.x13.asig.db.security.UserLogged;
import ro.x13.asig.db.service.AngajatService;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.service.UserService;
import ro.x13.asig.db.view.model.LoginModel;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class RoleResource {

    @Autowired
    private AngajatService angajatService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/role")
    public String edit(Model model, Authentication authentication) {
            UserLogged userLogged = (UserLogged) authentication.getPrincipal();
            Angajat angajat = angajatService.get(userLogged.getUsername());

            LoginModel loginModel = toModel(angajat);
            getCombos(model, loginModel, angajat);
            model.addAttribute("login", loginModel);
            return "role.form";
    }

    private void getCombos(Model model, LoginModel loginModel, Angajat angajat) {
        List<User> roles = userService.find(angajat);
        List<Unitate> unitateList = roles.stream().map(r -> r.getUnitate()).collect(Collectors.toList());
        List<Rol> rolList = roles.stream().map(r -> r.getRol()).collect(Collectors.toList());
        List<TextValueModel> unitateValueModelList = ServiceUtil.getTextValueModelList(unitateList);
        List<TextValueModel> rolValueModelList = ServiceUtil.getTextValueModelList(rolList);
        loginModel.setUnitateList(unitateValueModelList);
        loginModel.setRolList(rolValueModelList);
    }

    private LoginModel toModel(Angajat angajat) {
        return LoginModel.builder()
                .id(angajat.getId())
                .username(angajat.getUsername())
                .build();
    }


    @PostMapping(value = "/role/save")
    public String save(Model model, LoginModel loginModel, Authentication authentication) {
        UserLogged userLogged = (UserLogged) authentication.getPrincipal();

        userLogged.setRol(loginModel.getRol());
        userLogged.setUnitate(loginModel.getUnitate());
        return "redirect:/index";
    }

}
