package ro.x13.asig.db.dao.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "p_imobil")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imobil extends BaseDomain {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    @SequenceGenerator(name = "auto_gen", sequenceName = "p_imobil_seq", allocationSize = 1)
    //TODO automatic generator
    private Long id;


    @ManyToOne
    @JoinColumn(name = "tip_mediu_id", nullable = true)
    private MediuType mediu;

    @ManyToOne
    @JoinColumn(name = "constructie_id", nullable = true)
    private ConstructieType constructie;

    @ManyToOne
    @JoinColumn(name = "tip_structura_id", nullable = true)
    private StructuraType structuraType;


    private BigDecimal suprafata;
    private Integer anConstructie;
    private Integer nrCamere;
    private Integer nrEtaje;
    private Integer nrCladiri;
    private String mentiuni;
    private String vecinatate;

    public Map toMap() {
        Map m = new HashMap();
        m.put("id", getId());
        m.put("data", asList());
        return m;
    }

    public List asList() {
        List l = new ArrayList();
        //l.add(tipStrada == null ? null : tipStrada.getName());   //!
        //l.add(getJudet() == null ? null : getJudet().getName());   //!
        //l.add(getTara() == null ? null : getTara().getName()); //!
        return l;
    }
}
