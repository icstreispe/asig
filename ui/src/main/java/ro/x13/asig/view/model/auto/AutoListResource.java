package ro.x13.asig.view.model.auto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.x13.asig.db.dao.domain.Auto;
import ro.x13.asig.db.service.AutoService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ro.x13.asig.view.model.auto.AutoListResource.AUTO_URL;

@Controller
@RequiredArgsConstructor
@RequestMapping(AUTO_URL + "/list")
public class AutoListResource {

    public static final String AUTO_URL = "/auto";

    private final AutoService autoService;


    @RequestMapping(value = "", method = {GET, POST})
    public String load(Model model, AutoListModel autoModel) {
        Auto auto = getDomain(autoModel);
        Page<Auto> autoList = autoService.listAll(auto, autoModel.getPage(), 7);

        //load list
        autoModel.setDataList(autoList);

        //load combos
        getCombos(autoModel);

        model.addAttribute(autoModel.getModelName(), autoModel);
        return "admin/auto.list";
    }


    private void getCombos(AutoListModel autoModel) {
    }


    private Auto getDomain(AutoListModel autoModel) {
        return Auto.builder()
                .id(autoModel.getId())
                .serieCiv(autoModel.getSerieCiv().getValue())
                .build();
    }
}
