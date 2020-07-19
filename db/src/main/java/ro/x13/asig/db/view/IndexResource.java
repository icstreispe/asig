package ro.x13.asig.db.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexResource {



    @GetMapping(value = "/")
    public String index(Model model) {

        return "index";
    }


}
