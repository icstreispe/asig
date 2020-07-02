package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="n_tara")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tara extends CatalogDomain {

        private String name;
        private String nameEn;
        private Long population;
        private Long area;
}
