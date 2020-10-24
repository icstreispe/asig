package ro.x13.asig.db.dao.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import javax.persistence.*;

@Entity
@Table(name="bn_utilizare")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UtilizareType extends CatalogDomain {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)      //TODO automatic generator
        private Long id;

}
