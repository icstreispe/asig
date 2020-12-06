package ro.x13.asig.db.dao.domain.org;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnTransformer;
import ro.x13.asig.db.dao.domain.Angajat;
import ro.x13.asig.db.dao.domain.BaseDomain;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "o_password")
public class Password extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "password_gen")
    @SequenceGenerator(name = "password_gen", sequenceName = "o_password_seq", allocationSize = 1)
    private Long id;
/* TODO encryption
    @ColumnTransformer(
            read =  "pgp_sym_decrypt(" +
                    "    password, " +
                    "    current_setting('encrypt.key')" +
                    ")",
            write = "pgp_sym_encrypt( " +
                    "    ?, " +
                    "    current_setting('encrypt.key')" +
                    ") "
    )
 */
    @Column(columnDefinition = "password")
    private String password;


    @ManyToOne
    @JoinColumn(name = "idang", nullable = true)
    private Angajat angajat;



}
