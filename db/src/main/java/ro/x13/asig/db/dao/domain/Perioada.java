package ro.x13.asig.db.dao.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "n_perioada")
@Data
@SuperBuilder
@NoArgsConstructor
public class Perioada extends CatalogDomain {

    private Long luni;      //TODO combo tr ordonat dupa luni
}
