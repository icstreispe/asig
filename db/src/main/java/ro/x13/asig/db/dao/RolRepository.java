package ro.x13.asig.db.dao;

import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.Rol;
import ro.x13.asig.db.dao.domain.Unitate;

import java.util.List;


public interface RolRepository extends CrudRepository<Rol, Long> {

    List<Rol> findAllByOrderByNameAsc();
}

