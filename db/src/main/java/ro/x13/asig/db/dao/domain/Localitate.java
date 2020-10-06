package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="n_localitate")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Localitate extends CatalogDomain {


        private Long firms;
        private Long population;

        private Double longitude;
        private Double latitude;

        private String apartenenta;

        @ManyToOne
        @JoinColumn(name = "judet", nullable = true)
        private Judet judet;

}
