package ro.x13.asig.db.dao.domain.org;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="o_societate_type")
@Data
@SuperBuilder
@NoArgsConstructor
public class SocietateType extends CatalogDomain {


}
