package ro.x13.asig.db.dao.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import javax.persistence.*;

@Entity
@Table(name="bn_combustibil")
@Data
@SuperBuilder
@NoArgsConstructor
public class Combustibil extends CatalogDomain {

}
