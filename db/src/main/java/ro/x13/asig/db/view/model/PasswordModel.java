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
public class PasswordModel {

    private Long id;

    private String cnp;
    private String cod;
    private Long societate;
    private String nume;
    private String prenume;

    private String password;

    private List societateList;

    public void copy(PasswordModel a){
        id = a.id;
        cnp = a.cnp;
        cod = a.cod;
        societate = a.societate;
        nume = a.nume;
        prenume = a.prenume;
        password = a.password;
    }
}
