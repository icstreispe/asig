package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name="nt_strada")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TipStrada extends CatalogDomain {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)      //TODO automatic generator
        private Long id;

        private String name;

}
