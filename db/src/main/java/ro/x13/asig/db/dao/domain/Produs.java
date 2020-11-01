package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.org.Societate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity (name="b_produs")
//@SequenceGenerator(name = "generic", sequenceName = "produs_seq", allocationSize = 1)   //TODO mai merge cand sunt mai multe entities fiec cu seq ei?
public class Produs extends CatalogDomain{

//        @Id
        //@GeneratedValue(strategy = GenerationType.AUTO, generator = "n_asig_gen")
//        private Long id;


        @ManyToOne
        @JoinColumn(name = "tip_asig", nullable = true)
        private AsigType asigType;

        @ManyToOne
        @JoinColumn(name = "idsoc", nullable = true)
        private Societate societate;
}
