package ro.x13.asig.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ro.x13.asig.db.dao.AdresaRepository;
import ro.x13.asig.db.dao.domain.Adresa;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AdresaRepositoryTest {

    @Autowired
    AdresaRepository adresaRepository;

    @BeforeEach
    void init (){

    }

    @Test
    void whenSavedThenFound(){
        Adresa adresa = Adresa.builder().apartament("ap1").build();
        Adresa a2 = adresaRepository.save(adresa);
        assertNotNull (a2);

        List<Adresa> l = adresaRepository.findAllByOrderById();
        assertEquals(l.size(), 1);
        assertEquals(l.get(0), a2);
        //TODO test other fields
    }

    @Test
    void whenNotSavedThenNotFound (){
        List<Adresa> l = adresaRepository.findAllByOrderById();
        assertEquals(l.size(), 0);
    }

}