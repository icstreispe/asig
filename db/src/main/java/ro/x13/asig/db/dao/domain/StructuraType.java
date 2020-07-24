package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import javax.persistence.*;

@Entity
@Table(name="nt_structura")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StructuraType extends CatalogDomain {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)      //TODO automatic generator
        private Long id;

        private String name;

}
