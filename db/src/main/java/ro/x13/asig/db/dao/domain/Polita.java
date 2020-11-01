package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.org.Societate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity (name="p_polita")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Polita implements Domain {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;

        private String serie;
        private Integer nr;

        private Long tipPlata;  //TODO

        @ManyToOne
        @JoinColumn(name = "idsoc", nullable = true)
        private Societate societate;

        @ManyToOne
        @JoinColumn(name = "idprodus", nullable = true)
        private Produs produs;

        private BigDecimal sumaAsig;

        @ManyToOne
        @JoinColumn(name = "moneda", nullable = true)
        private Moneda moneda;

        @ManyToOne
        @JoinColumn(name = "perioada", nullable = true)
        private Perioada perioada;


        private Date startValid;
        private Date endValid;
        private Date emisLa;



}
