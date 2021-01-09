package ro.x13.asig.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.x13.asig.db.service.JudetService;
import ro.x13.asig.db.service.StructuraTypeService;
import ro.x13.asig.db.service.TaraService;
import ro.x13.asig.view.model.ComboView;
import ro.x13.asig.view.model.StringFieldView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ThymeResource {

    @Autowired
    private StructuraTypeService structuraTypeService;
    @Autowired
    private TaraService taraService;
    @Autowired
    private JudetService judetService;

    @GetMapping(value = "/adresaDynamic")
    public String index(Model model) {
        List fields = new ArrayList();
        fields.add(ComboView.builder().nameKey("structuraType").list(structuraTypeService.listCombo()).build());
        fields.add(ComboView.builder().nameKey("tara").list(taraService.listCombo()).build());
        fields.add(ComboView.builder().nameKey("judet").list(judetService.listCombo()).build());
            //<tr th:insert="~{form::comboField(key='tipStrada',field='tipStrada',list=null)}"></tr>


        fields.add(StringFieldView.builder().nameKey("localitate").size(50).value("Pitesti").build());
        fields.add(StringFieldView.builder().nameKey("sublocalitate").size(50).value("Vedea").build());

        fields.add(StringFieldView.builder().nameKey("strada").size(10).value("Vedea").build());
        fields.add(StringFieldView.builder().nameKey("numar").size(10).value("26").build());
        fields.add(StringFieldView.builder().nameKey("bloc").size(10).value("P66").build());
        fields.add(StringFieldView.builder().nameKey("scara").size(10).value("2").build());
        fields.add(StringFieldView.builder().nameKey("etaj").size(10).value("7").build());

        fields.add(StringFieldView.builder().nameKey("apartament").size(10).value("13").build());
        fields.add(StringFieldView.builder().nameKey("codPostal").size(10).value("512342").build());

        model.addAttribute("fields", fields);

        return "adresaDynamic";
    }


}
