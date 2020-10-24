package ro.x13.asig.db.dao.biz;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="bn_categorie_auto")        //TODO business?
@Data
@SuperBuilder
@NoArgsConstructor
public class CategorieAuto extends CatalogDomain {


}
