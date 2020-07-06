package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="nt_constructie")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConstructieType extends CatalogDomain {

        private String name;

}
