package ro.x13.asig.db.dao.domain.meta;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_action")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Action extends CatalogDomain {


    private String code;

    private Long type;  //TODO for now

}
