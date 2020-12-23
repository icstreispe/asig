package ro.x13.asig.db.dao.domain.org;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.BaseDomain;

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
    @JoinColumn(name = "idang")
    private Angajat angajat;

    @ManyToOne
    @JoinColumn(name = "idunitate")
    private Unitate unitate;                  //TODO spart si salvat in adresa?

    @ManyToOne
    @JoinColumn(name = "idrol")
    private Rol rol;

    @Column(name = "b_default")
    private Boolean isDefault;


}
