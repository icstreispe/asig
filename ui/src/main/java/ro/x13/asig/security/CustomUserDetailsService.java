package ro.x13.asig.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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

    //TODO deocamdata clasa pusa aici pentru ca foloseste si db si security

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AngajatService angajatService;
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Angajat angajat = angajatService.get(username);
        List<User> roles = userService.find(angajat);
        List<SimpleGrantedAuthority> auths = roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRol().getName()))
                .collect(Collectors.toList());

        UserLogged userLogged =  new UserLogged(username, passwordEncoder.encode(angajat.getPassword().getPassword()),
                auths);
        userLogged.setNume(angajat.getNume());
        userLogged.setPrenume(angajat.getPrenume());
        return userLogged;
    }
}
