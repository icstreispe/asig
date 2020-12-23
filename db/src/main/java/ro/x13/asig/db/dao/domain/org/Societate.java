package ro.x13.asig.db.dao.domain.org;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.CatalogDomain;
import ro.x13.asig.db.dao.domain.Juridic;

import javax.persistence.*;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity (name="o_societate")
@SequenceGenerator(name = "generic", sequenceName = "o_societate_seq", allocationSize = 1)   //TODO mai merge cand sunt mai multe entities fiec cu seq ei?
public class Societate extends CatalogDomain {

//        @Id
        //@GeneratedValue(strategy = GenerationType.AUTO, generator = "n_asig_gen")
//        private Long id;


        private String adresa;                  //TODO spart si salvat in adresa?
        private String regCom;
        private String cif;
        private String telefon;
        private String fax;

        @ManyToOne
        @JoinColumn(name = "juridic")
        private Juridic juridic;

        @ManyToOne
        @JoinColumn(name = "tip")
        private SocietateType tip;
}
