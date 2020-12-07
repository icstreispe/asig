package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.UserRepository;
import ro.x13.asig.db.dao.domain.org.Angajat;
import ro.x13.asig.db.dao.domain.org.User;

import java.util.List;

@Service
public class UserService extends CrudService<User> {

    @Autowired
    private UserRepository repository;

    @Override
    public CrudRepository<User, Long> getRepo() {
        return repository;
    }

    public List<User> list() {
        return repository.findAllByOrderByIdAsc();
    }

    public User load(Long id) {
        return repository.findById(id).get();
    }

    public List<User> findAll(User user) {
        return repository.findAllByOrderByIdAsc();
    }

    public List<User> find(Angajat a ) {
        return repository.findByAngajat(a);
    }


}
