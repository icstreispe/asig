package ro.x13.asig.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.x13.asig.db.service.JudetService;
import ro.x13.asig.db.service.StructuraTypeService;
import ro.x13.asig.db.service.TaraService;
import ro.x13.asig.view.model.FieldView;

import java.util.ArrayList;
import java.util.List;

import static ro.x13.asig.view.model.FieldType.LIST;
import static ro.x13.asig.view.model.FieldType.TEXT;


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
        fields.add(FieldView.builder().type(LIST).nameKey("structuraType").list(structuraTypeService.listCombo()).build());
        fields.add(FieldView.builder().type(LIST).nameKey("tara").list(taraService.listCombo()).build());
        fields.add(FieldView.builder().type(LIST).nameKey("judet").list(judetService.listCombo()).build());
            //<tr th:insert="~{form::comboField(key='tipStrada',field='tipStrada',list=null)}"></tr>


        fields.add(FieldView.builder().type(TEXT).nameKey("localitate").size(50).value("Pitesti").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("sublocalitate").size(50).value("Vedea").build());

        fields.add(FieldView.builder().type(TEXT).nameKey("strada").size(10).value("Vedea").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("numar").size(10).value("26").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("bloc").size(10).value("P66").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("scara").size(10).value("2").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("etaj").size(10).value("7").build());

        fields.add(FieldView.builder().type(TEXT).nameKey("apartament").size(10).value("13").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("codPostal").size(10).value("512342").build());

        model.addAttribute("fields", fields);

        return "adresaDynamic";
    }


}
