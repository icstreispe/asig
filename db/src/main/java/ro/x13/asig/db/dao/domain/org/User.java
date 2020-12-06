package ro.x13.asig.db.dao.domain.org;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.Angajat;
import ro.x13.asig.db.dao.domain.BaseDomain;
import ro.x13.asig.db.dao.domain.org.Rol;
import ro.x13.asig.db.dao.domain.org.Unitate;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "o_user")
public class User extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "o_user_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idang", nullable = true)
    private Angajat angajat;

    @ManyToOne
    @JoinColumn(name = "idunitate", nullable = true)
    private Unitate unitate;                  //TODO spart si salvat in adresa?

    @ManyToOne
    @JoinColumn(name = "idrol", nullable = true)
    private Rol rol;


}
