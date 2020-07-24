package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name="n_tara")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Tara extends  CatalogDomain {


        private String nameEn;
        private Long population;
        private Long area;
}
