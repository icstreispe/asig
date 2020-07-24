package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name="n_judet")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Judet extends CatalogDomain {


        private String cod;
}
