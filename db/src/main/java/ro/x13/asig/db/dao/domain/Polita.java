package ro.x13.asig.db.dao.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
        private Long idPolita;

        private String serie;
        private Integer nr;
        private Date startValid;
        private Date endValid;
        private Date emisLa;
        private Long tipPlata;  //TODO


}