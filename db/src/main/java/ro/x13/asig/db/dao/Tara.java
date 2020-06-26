package ro.x13.asig.db.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="n_tara")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tara {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;

        private String name;
        private String nameEn;
        private Long population;
        private Long area;

        private Date startDate;
        private Date endDate;
}
