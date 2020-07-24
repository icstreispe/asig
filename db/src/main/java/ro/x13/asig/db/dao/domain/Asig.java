package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity (name="n_asig")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "generic", sequenceName = "n_asig_seq", allocationSize = 1)   //TODO mai merge cand sunt mai multe entities fiec cu seq ei?
public class Asig extends CatalogDomain{

//        @Id
        //@GeneratedValue(strategy = GenerationType.AUTO, generator = "n_asig_gen")
//        private Long id;


        private String cui;
        private String telefon;
        private String fax;
}
