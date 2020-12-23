package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.biz.ClasaBm;
import ro.x13.asig.db.dao.domain.org.Societate;
import ro.x13.asig.db.dao.domain.org.User;

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
        @JoinColumn(name = "idsoc")
        private Societate societate;

        @ManyToOne
        @JoinColumn(name = "idprodus")
        private Produs produs;

        private BigDecimal sumaAsig;

        @ManyToOne
        @JoinColumn(name = "moneda")
        private Moneda moneda;

        @ManyToOne
        @JoinColumn(name = "perioada")
        private Perioada perioada;

        @ManyToOne
        @JoinColumn(name = "clasa_bm")
        private ClasaBm clasaBm;

        @ManyToOne
        @JoinColumn(name = "start_user")
        private User startUser;


        private Date startValid;
        private Date endValid;
        private Date emisLa;



}
