package ro.x13.asig.db.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tip_structura")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructuraType {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;

        private String name;
        private Date startDate;
        private Date endDate;

}
