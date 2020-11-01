package ro.x13.asig.db.dao;

import org.springframework.data.repository.CrudRepository;
import ro.x13.asig.db.dao.domain.org.User;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAllByOrderByIdAsc();
}

