package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity (name="o_asig")
@SequenceGenerator(name = "generic", sequenceName = "o_asig_seq", allocationSize = 1)   //TODO mai merge cand sunt mai multe entities fiec cu seq ei?
public class Asig extends CatalogDomain{

//        @Id
        //@GeneratedValue(strategy = GenerationType.AUTO, generator = "n_asig_gen")
//        private Long id;


        private String adresa;                  //TODO spart si salvat in adresa?
        private String regCom;
        private String cif;
        private String telefon;
        private String fax;

        @ManyToOne
        @JoinColumn(name = "juridic", nullable = true)
        private Juridic juridic;

        @ManyToOne
        @JoinColumn(name = "tip", nullable = true)
        private SocietateType tip;
}
