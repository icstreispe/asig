package ro.x13.asig.db.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.x13.asig.db.service.PolitaModel;
import ro.x13.asig.db.service.PolitaService;
import ro.x13.asig.db.service.StructuraTypeService;
import ro.x13.asig.db.service.TaraService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ThymeResource {

    @Autowired
    private StructuraTypeService structuraTypeService;
    @Autowired
    private TaraService taraService;

    @GetMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("structuraTypeList", structuraTypeService.listCombo());
        model.addAttribute("taraList", taraService.listCombo());
        return "/index";
    }


}
