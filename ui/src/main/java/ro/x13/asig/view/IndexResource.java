package ro.x13.asig.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexResource {



    @GetMapping(value = {"/index", "/"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = "/angular")
    public String angular(Model model) {
        return "/angular";
    }

    @GetMapping(value = "/content")
    public String content(Model model) {
        return "/content";
    }


}
