package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import javax.persistence.*;

@Entity
@Table(name="tip_structura")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructuraType extends CatalogDomain {

        private String name;

}
