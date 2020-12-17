package ro.x13.asig.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.x13.asig.db.service.*;
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

    @GetMapping(value = "/thyme")
    public String index(Model model) {
        List fields = new ArrayList();
        fields.add(FieldView.builder().type(LIST).name("structuraType").list(structuraTypeService.listCombo()).build());
        fields.add(FieldView.builder().type(LIST).name("tara").list(taraService.listCombo()).build());
        fields.add(FieldView.builder().type(LIST).name("judet").list(judetService.listCombo()).build());
            //<tr th:insert="~{form::comboField(key='tipStrada',field='tipStrada',list=null)}"></tr>


        fields.add(FieldView.builder().type(TEXT).name("localitate").size(50).value("Pitesti").build());
        fields.add(FieldView.builder().type(TEXT).name("sublocalitate").size(50).value("Vedea").build());

        fields.add(FieldView.builder().type(TEXT).name("strada").size(10).value("Vedea").build());
        fields.add(FieldView.builder().type(TEXT).name("numar").size(10).value("26").build());
        fields.add(FieldView.builder().type(TEXT).name("bloc").size(10).value("P66").build());
        fields.add(FieldView.builder().type(TEXT).name("scara").size(10).value("2").build());
        fields.add(FieldView.builder().type(TEXT).name("etaj").size(10).value("7").build());

        fields.add(FieldView.builder().type(TEXT).name("apartament").size(10).value("13").build());
        fields.add(FieldView.builder().type(TEXT).name("codPostal").size(10).value("512342").build());

        model.addAttribute("fields", fields);

    //<div th:replace="~{form::inputFieldDynamic(f=${field})}"></div>


        return "adresa";
    }


}
