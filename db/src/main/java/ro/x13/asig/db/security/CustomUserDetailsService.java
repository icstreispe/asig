package ro.x13.asig.db.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.domain.org.Angajat;
import ro.x13.asig.db.dao.domain.org.User;
import ro.x13.asig.db.service.AngajatService;
import ro.x13.asig.db.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AngajatService angajatService;
    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Angajat angajat = angajatService.findByUsername(username);
        List<User> roles = userService.find(angajat);
        List<SimpleGrantedAuthority> auths = roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRol().getName()))
                .collect(Collectors.toList());;

        CustomUserDetails customUserDetails =  new CustomUserDetails(username, passwordEncoder.encode(angajat.getPassword().getPassword()),
                auths);
        customUserDetails.setNume(angajat.getNume());
        customUserDetails.setPrenume(angajat.getPrenume());
        return customUserDetails;
    }
}
