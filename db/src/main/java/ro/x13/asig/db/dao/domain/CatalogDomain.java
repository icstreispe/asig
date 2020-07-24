package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data   //getter & setter
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class CatalogDomain extends BaseDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="generic")      //TODO automatic generator
    private Long id;

    private String name;

}
