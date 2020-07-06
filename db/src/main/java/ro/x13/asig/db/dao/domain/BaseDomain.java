package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
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
public class BaseDomain {


    private Date startDate;
    private Date updateDate;    //TODO initializarea
    private Date endDate;
}
