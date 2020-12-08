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
public class LoginModel {

    private Long id;
    private String username;
    private String password;
    private Long unitate;
    private Long rol;


    private List unitateList;
    private List rolList;
    private List list;
}
