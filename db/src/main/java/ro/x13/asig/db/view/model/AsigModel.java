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
    private String cui;
    private String telefon;
    private String fax;

    private List asigList;

    public void copy(AsigModel a){
        id = a.id;
        nume = a.nume;
        telefon = a.telefon;
        fax = a.fax;

        cui = a.cui;

    }
}
