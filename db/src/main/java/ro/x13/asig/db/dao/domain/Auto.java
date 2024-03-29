package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.biz.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity (name="p_auto")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Auto extends BaseDomain {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;


        @ManyToOne
        @JoinColumn(name = "idpolita")
        private Polita polita;                  //TODO probabil invers lista/polita ?

        @ManyToOne
        @JoinColumn(name = "marca")
        private Marca marca;

        private String model;

        @ManyToOne
        @JoinColumn(name = "categorie")
        private CategorieAuto categorieAuto;

        @ManyToOne
        @JoinColumn(name = "tip")
        private TipAuto tipAuto;

        @ManyToOne
        @JoinColumn(name = "stare_matric")
        private StareMatric stareMatric;

        private String nrMatric;
        private String serieSasiu;
        private String serieCiv;

        private Integer nrLocuri;
        private Integer masaMax;
        private Integer cilindree;
        private Integer putere;
        private Integer anFabricatie;

        @ManyToOne
        @JoinColumn(name = "combustibil")
        private Combustibil combustibil;

        @ManyToOne
        @JoinColumn(name = "utilizare")
        private UtilizareType utilizare;


}
