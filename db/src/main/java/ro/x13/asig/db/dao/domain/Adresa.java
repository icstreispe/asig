package ro.x13.asig.db.dao.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "p_adresa")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Adresa extends CatalogDomain {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    @SequenceGenerator(name = "auto_gen", sequenceName = "p_adresa_seq", allocationSize = 1)    //TODO automatic generator
    private Long id;

    private String localitate;
    private String sublocalitate;

    @ManyToOne
    @JoinColumn(name = "tara_id", nullable = true)
    private Tara tara;

    @ManyToOne
    @JoinColumn(name = "judet_id", nullable = true)
    private Judet judet;

    @ManyToOne
    @JoinColumn(name = "tip_strada_id", nullable = true)
    private TipStrada tipStrada;

    private String strada;
    private String numar;
    private String bloc;
    private String scara;
    private String etaj;
    private String apartament;
    private String codPostal;

    public Map toMap() {
        Map m = new HashMap();
        m.put("id", getId());
        m.put("data", asList());
        return m;
    }

    public List asList() {
        List l = new ArrayList();
        l.add(tipStrada==null? null : tipStrada.getName());   //!
        l.add(getStrada());
        l.add(getNumar());
        l.add(getBloc());
        l.add(getScara());
        l.add(getEtaj());
        l.add(getApartament());
        l.add(getLocalitate());
        l.add(getSublocalitate());
        l.add(getJudet()==null? null : getJudet().getName());   //!
        l.add(getCodPostal());
        l.add(getTara() == null ? null : getTara().getName()); //!
        return l;
    }
}
