package ro.x13.asig.db.dao.domain.biz;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="bn_clasa_bm")
@Data
@SuperBuilder
@NoArgsConstructor
public class ClasaBm extends CatalogDomain {



}
