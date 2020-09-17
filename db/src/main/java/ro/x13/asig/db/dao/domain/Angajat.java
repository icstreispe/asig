package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity (name="o_angajat")
@SequenceGenerator(name = "generic", sequenceName = "o_angajat_seq", allocationSize = 1)
public class Angajat extends BaseDomain{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
        @SequenceGenerator(name = "auto_gen", sequenceName = "o_angajat_seq", allocationSize = 1)    //TODO automatic generator
        private Long id;

        private String cnp;                  //TODO spart si salvat in adresa?

        private String cod;             //cod asig
        private String nume;
        private String prenume;

        private String ciSerie; //TODO straini?
        private String ciNumar; //TODO straini?

        @ManyToOne
        @JoinColumn(name = "idsoc", nullable = true)
        private Societate societate;

            //tip integer,

        private String telefon;
        private String email;


        /**
         * utility function to get tge full name for presentation
         */
        public String getLabel() {
                return getNume() + " " + getPrenume();       //TODO for now
        }

}
