package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="n_judet")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Judet extends CatalogDomain {


        private String name;
        private String cod;
}
