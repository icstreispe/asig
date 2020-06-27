package ro.x13.asig.db.dao;

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
public class CatalogDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    private Date startDate;
    private Date endDate;
}
