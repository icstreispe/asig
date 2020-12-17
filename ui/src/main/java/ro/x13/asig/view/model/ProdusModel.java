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
public class ProdusModel {

    private Long id;

    private Long societate;
    private Long asigType;
    private String name;

    private List list;

    private List societateList;
    private List asigTypeList;

    public void copy(ProdusModel a){
        id = a.id;
        name = a.name;
        asigType = a.asigType;
        societate = a.societate;
    }
}
