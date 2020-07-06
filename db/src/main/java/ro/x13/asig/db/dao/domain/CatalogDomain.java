package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data   //getter & setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class CatalogDomain extends BaseDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)      //TODO automatic generator
    private Long id;


}
