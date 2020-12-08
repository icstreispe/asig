package ro.x13.asig.db.security;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Data
public class UserLogged extends User {
    private String nume;
    private String prenume;
    private Long rol;
    private Long unitate;

    public UserLogged(final String username, final String password,
                      final List<SimpleGrantedAuthority> authorities) {
        super(username, password, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, authorities);
    }


}
