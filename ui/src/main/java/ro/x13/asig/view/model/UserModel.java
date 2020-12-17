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
public class UserModel {

    private Long id;

    private Long angajat;
    private Long unitate;
    private Long rol;

    private List list;

    private List unitateList;
    private List rolList;
    private List angajatList;

    public void copy(UserModel a){
        id = a.id;
        angajat = a.angajat;
        unitate = a.unitate;
        rol = a.rol;
    }
}
