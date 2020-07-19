package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity (name="p_persoana")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persoana {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "pers_gen")
        @SequenceGenerator(name = "pers_gen", sequenceName = "p_persoana_seq", allocationSize = 1)
        private Long id;

        private String cnp;
        private String nume;
        private String prenume;

        private String ciSerie; //TODO straini?
        private String ciNumar; //TODO straini?

        private Integer anPermis;
        private Integer nrCopii;

        @ManyToOne
        @JoinColumn(name = "adresa_id", nullable = true)
        private Adresa adresa;

        @ManyToOne
        @JoinColumn(name = "cetatenie_id", nullable = true)
        private Tara cetatenie;

        @ManyToOne
        @JoinColumn(name = "nationalitate_id", nullable = true)
        private Tara nationalitate;

        private String telefon;
        private String fax;
        private String email;

    //b_strain boolean,
        @Column(name = "b_bugetar")
        private Boolean bugetar;

        @Column(name = "b_politic")
        private Boolean politic;

}
