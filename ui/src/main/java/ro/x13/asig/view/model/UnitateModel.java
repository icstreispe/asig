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
public class UnitateModel {

    private Long id;

    private Long societate;

    private String name;
    private String adresa;                  //TODO spart (si salvat in alta parte)
    private String telefon;
    private String fax;

    private List list;

    private List societateList;

    public void copy(UnitateModel a){
        id = a.id;
        adresa = a.adresa;
        telefon = a.telefon;
        fax = a.fax;
        name = a.name;
    }
}
