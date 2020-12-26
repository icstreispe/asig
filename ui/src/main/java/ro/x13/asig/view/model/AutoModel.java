package ro.x13.asig.view.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoModel {

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
    
    public String getTitleKey (){
        return "auto.list.title";
    }

    public String getBaseKey (){
        return "auto";
    }

    public String getEditAction (){
        return "/auto";
    }
}
