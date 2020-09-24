package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "o_user")
@SequenceGenerator(name = "generic", sequenceName = "o_user_seq", allocationSize = 1)
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