package ro.x13.asig.db.dao.domain.org;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.CatalogDomain;
import ro.x13.asig.db.dao.domain.org.Societate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "o_unitate")
@SequenceGenerator(name = "generic", sequenceName = "o_unitate_seq", allocationSize = 1)
public class Unitate extends CatalogDomain {

//        @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "n_asig_gen")
//        private Long id;


    //private String name;

    @ManyToOne
    @JoinColumn(name = "idsoc", nullable = true)
    private Societate societate;

    private String adresa;                  //TODO spart si salvat in adresa?
    private String telefon;
    private String fax;

}
