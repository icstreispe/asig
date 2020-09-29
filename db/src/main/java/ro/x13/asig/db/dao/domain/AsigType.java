package ro.x13.asig.db.dao.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="nt_asig")
@Data
@SuperBuilder
@NoArgsConstructor
public class AsigType extends  CatalogDomain{


}
