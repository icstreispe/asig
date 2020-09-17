package ro.x13.asig.db.dao.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity (name="polite")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Polita {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;

        private String serie;
        private Integer nr;

        private Long tipPlata;  //TODO

        @ManyToOne
        @JoinColumn(name = "idsoc", nullable = true)
        private Societate societate;

        private BigDecimal sumaAsig;

        private Date startValid;
        private Date endValid;
        private Date emisLa;



}
