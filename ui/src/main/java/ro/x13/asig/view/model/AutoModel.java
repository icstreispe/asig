package ro.x13.asig.view.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static ro.x13.asig.view.model.FieldType.LIST;
import static ro.x13.asig.view.model.FieldType.TEXT;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoModel implements FormModel {

    public static final String MODEL_NAME = "auto";
    private Long id;

    private Long marca;
    private Long categorieAuto;
    private Long tipAuto;
    private Long stareMatric;
    private Long combustibil;
    private Long utilizare;

    private String model;
    private String nrMatric;
    private String serieSasiu;
    private String serieCiv;

    private Integer nrLocuri;
    private Integer masaMax;
    private Integer cilindree;
    private Integer putere;
    private Integer anFabricatie;


    private List categorieAutoList;
    private List marcaList;
    private List stareMatricList;
    private List tipAutoList;
    private List combustibilList;
    private List utilizareList;

    private List list;

    public String[] getHeaders (){
        return new String[] {"marca", "model", "categorieAuto", "tipAuto", "stareMatric",
                "nrMatric", "serieCiv", "serieSasiu", "putere", "cilindree", "masaMax", 
                "nrLocuri", "anFabricatie", "combustibil", "utilizare"};
    }

    public List<FieldView> getFields (){
        List fields = new ArrayList();
        fields.add(FieldView.builder().type(LIST).nameKey("auto.marca").name("marca").list(marcaList).build());
        fields.add(FieldView.builder().type(TEXT).nameKey("auto.model").name("model").build());
        fields.add(FieldView.builder().type(LIST).nameKey("auto.categorieAuto").name("categorieAuto").list(categorieAutoList).clasa("ajax").build());
        fields.add(FieldView.builder().type(LIST).nameKey("auto.tipAuto").name("tipAuto").list(tipAutoList).build());
        fields.add(FieldView.builder().type(LIST).nameKey("auto.stareMatric").name("stareMatric").list(stareMatricList).build());
        fields.add(FieldView.builder().type(TEXT).nameKey("auto.nrMatric").name("nrMatric").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("auto.serieCiv").name("serieCiv").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("auto.serieSasiu").name("serieSasiu").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("auto.putere").name("putere").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("auto.cilindree").name("cilindree").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("auto.masaMax").name("masaMax").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("auto.nrLocuri").name("nrLocuri").build());
        fields.add(FieldView.builder().type(TEXT).nameKey("auto.anFabricatie").name("anFabricatie").build());
        fields.add(FieldView.builder().type(LIST).nameKey("auto.combustibil").name("combustibil").list(combustibilList).build());
        fields.add(FieldView.builder().type(LIST).nameKey("auto.utilizare").name("utilizare").list(utilizareList).build());
        return fields;
    }
    
    public String getListTitleKey (){
        return "auto.list.title";
    }

    public String getBaseKey (){
        return "auto";
    }

    public String getEditAction (){
        return "/auto";
    }

    public String getFormTitleKey (){
        return "auto.form.title";
    }

    public String getModelName (){
        return MODEL_NAME;
    }
}
