package ro.x13.asig.db.security;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Data
public class CustomUserDetails extends User {
    private String nume;
    private String prenume;

    public CustomUserDetails(final String username, final String password,
                             final List<SimpleGrantedAuthority> authorities) {
        super(username, password, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, authorities);
        //this.nume = nume;
        //this.prenume = prenume;
    }


}
