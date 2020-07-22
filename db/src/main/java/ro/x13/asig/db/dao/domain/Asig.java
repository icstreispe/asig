package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity (name="n_asig")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asig extends BaseDomain {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "n_asig_gen")
        @SequenceGenerator(name = "n_asig_gen", sequenceName = "n_asig_seq", allocationSize = 1)
        private Long id;

        private String nume;
        private String cui;
        private String telefon;
        private String fax;


}
