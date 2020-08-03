package ro.x13.asig.db.view.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsigModel {

    private Long id;


    private String nume;
    private String cif;
    private String adresa;
    private String regCom;
    private String telefon;
    private String fax;

    private Long tip;
    private Long juridic;

    private List juridicList;
    private List tipList;
    private List asigList;

    public void copy(AsigModel a){
        id = a.id;
        nume = a.nume;
        telefon = a.telefon;
        fax = a.fax;

        cif = a.cif;
        adresa = a.adresa;
        regCom = a.regCom;

        tip = a.tip;
        juridic = a.juridic;

    }
}