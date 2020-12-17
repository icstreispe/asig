package ro.x13.asig.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ro.x13.asig.db.dao.domain.org.Angajat;
import ro.x13.asig.db.dao.domain.org.Rol;
import ro.x13.asig.db.dao.domain.org.Unitate;
import ro.x13.asig.db.dao.domain.org.User;
import ro.x13.asig.db.service.AngajatService;
import ro.x13.asig.db.service.RolService;
import ro.x13.asig.db.service.UnitateService;
import ro.x13.asig.db.service.UserService;
import ro.x13.asig.view.model.UserModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private UserService userService;
    @Autowired
    private UnitateService unitateService;
    @Autowired
    private RolService rolService;
    @Autowired
    private AngajatService angajatService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));   //sets the POSTed objects null instead of empty
    }


    @GetMapping(value = "")
    public String add(UserModel userModel, Model model) {

        getCombos(model, userModel);

        model.addAttribute("user", userModel);
        return "admin/user.form";
    }


    @GetMapping(value = "/list")
    public String list(Model model) {
        UserModel userModel = new UserModel();
        List<User> list = userService.list();
        userModel.setList(getList(list));

        getCombos(model, userModel);

        model.addAttribute("user", userModel);
        return "admin/user.list";
    }

    @PostMapping(value = "/list")
    public String filter(Model model, UserModel userModel) {
        User user = buildDomain(userModel);
        List<User> userList = userService.findAll(user);
        List<Map> userListMap = getList(userList);
        userModel.setList(userListMap);
        getCombos(model, userModel);

        model.addAttribute("user", userModel);
        return "admin/user.list";
    }


    @GetMapping(value = "/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        User user = userService.load(id);
        UserModel userModel = toModel(user);

        getCombos(model, userModel);

        model.addAttribute("user", userModel);
        return "admin/user.form";
    }


    @PostMapping(value = "")
    public String save(UserModel userModel) {
        User user = buildDomain(userModel);
        userService.save(user);
        return "redirect:/user/list";
    }

    private void getCombos(Model model, UserModel userModel) {
        userModel.setRolList(rolService.listCombo());
        model.addAttribute("rolList", userModel.getRolList());
        userModel.setAngajatList(angajatService.listCombo());
        model.addAttribute("angajatList", userModel.getAngajatList());
        userModel.setUnitateList(unitateService.listCombo());
        model.addAttribute("unitateList", userModel.getUnitateList());
    }

    private List<Map> getList(List<User> list) {
        return StreamSupport.stream(list.spliterator(), false)
                .map(a -> toView(a))       // pt list(lista()): Asig::toMap
                .collect(Collectors.toList());
    }

    private User buildDomain(UserModel userModel) {
        Angajat angajat = angajatService.load(userModel.getAngajat());
        Rol rol = rolService.get(userModel.getRol());
        Unitate unitate = unitateService.get(userModel.getUnitate());
        return User.builder()
                .id(userModel.getId())
                .unitate(unitate)
                .angajat(angajat)
                .rol(rol)
                .build();
    }


    private Map toView(User user) {
        Map m = new HashMap();
        m.put("id", user.getId());
        m.put("angajat", user.getAngajat() == null ? null : user.getAngajat().getLabel());
        m.put("rol", user.getRol() == null ? null : user.getRol().getName());
        m.put("unitate", user.getUnitate() == null ? null : user.getUnitate().getName());
        return m;
    }


    private UserModel toModel(User user) {
        return UserModel.builder()
                .id(user.getId())
                .angajat(user.getAngajat() == null ? null : user.getAngajat().getId())
                .rol(user.getRol() == null ? null : user.getRol().getId())
                .unitate(user.getUnitate() == null ? null : user.getUnitate().getId())

                .build();
    }
}
