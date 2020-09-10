package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "o_user")
@SequenceGenerator(name = "generic", sequenceName = "o_user_seq", allocationSize = 1)
public class User extends CatalogDomain {

//        @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "n_asig_gen")
//        private Long id;


    private String name;

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
