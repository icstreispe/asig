package ro.x13.asig.db.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdresaModel {

    private Long id;
    private String localitate;
    private String sublocalitate;

    private Long tara;
    private Long judet;
    private Long tipStrada;

    private String strada;
    private String numar;
    private String bloc;
    private String scara;
    private String etaj;
    private String apartament;
    private String codPostal;

    private List adresaList;
    private List judetList;
    private List List;
    private List tipStradaList;
    private List taraList;

    public void copy(AdresaModel a){
        id = a.id;
        localitate = a.localitate;
        sublocalitate = a.sublocalitate;
        tara = a.tara;
        judet = a.judet;
        strada = a.strada;
        numar = a.numar;
        bloc = a.bloc;
        scara = a.scara;
        etaj = a.etaj;
        apartament = a.apartament;
        codPostal = a.codPostal;
    }
}
