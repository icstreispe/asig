package ro.x13.asig.db.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.x13.asig.db.service.PolitaModel;
import ro.x13.asig.db.service.PolitaService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ThymeResource {

    @GetMapping(value = "/index")
    public String index(Model model) {
        List list = getList();
        model.addAttribute("stareMatricList", list);
        return "/index";
    }

    private List getList() {
        List list = new ArrayList<>();
        addElement (list, "inmatriculat", 1);
        addElement (list, "inreg la primarie", 2);
        addElement (list, "in vederea inreg", 3);
        addElement (list, "in vederea inmatric", 3);
        return list;
    }

    private void addElement(List list, String label, int value) {
        Map m = new HashMap<>();
        m.put ("value", value);
        m.put ("text", label);
        list.add(m);
    }


}
