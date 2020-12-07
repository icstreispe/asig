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
public class AngajatModel {

    private Long id;

    private String cnp;
    private String cod;
    private Long societate;
    private String username;
    private String nume;
    private String prenume;
    private String ciSerie;
    private String ciNumar;
    private String telefon;
    private String email;

    private List list;
    private List societateList;

    public void copy(AngajatModel a){
        id = a.id;
        cnp = a.cnp;
        cod = a.cod;
        societate = a.societate;
        ciNumar = a.ciNumar;
        ciSerie = a.ciSerie;
        nume = a.nume;
        prenume = a.prenume;
        telefon = a.telefon;
        email = a.email;
    }
}
