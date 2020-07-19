package ro.x13.asig.db.view.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.x13.asig.db.dao.domain.Adresa;
import ro.x13.asig.db.dao.domain.Tara;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersoanaModel {

    private Long id;

    private Long adresa;
    private Long cetatenie;
    private Long nationalitate;

    private String cnp;
    private String nume;
    private String prenume;

    private String ciSerie; //TODO straini?
    private String ciNumar; //TODO straini?

    private Integer anPermis;
    private Integer nrCopii;

    private String telefon;
    private String fax;
    private String email;

    private Boolean politic;
    private Boolean bugetar;

    private List persoanaList;
    private List cetatenieList;
    private List nationalitateList;
    //private List taraList;

    public void copy(PersoanaModel a){
        id = a.id;
        adresa = a.adresa;
        cetatenie = a.cetatenie;
        nationalitate = a.nationalitate;

        cnp = a.cnp;
        nume = a.nume;
        prenume = a.prenume;

        ciNumar = a.ciNumar;
        ciSerie = a.ciSerie;

        anPermis = a.anPermis;
        nrCopii = a.nrCopii;

        telefon = a.telefon;
        fax = a.fax;
        email = a.email;
    }
}
