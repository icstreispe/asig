package ro.x13.asig.db.dao.biz;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="bn_marca")
@Data
@SuperBuilder
@NoArgsConstructor
public class Marca extends CatalogDomain {


}
