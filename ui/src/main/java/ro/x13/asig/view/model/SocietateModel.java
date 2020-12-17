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
public class SocietateModel {

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

    private List list;

    public void copy(SocietateModel a){
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
