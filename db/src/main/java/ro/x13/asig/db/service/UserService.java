package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.UserRepository;
import ro.x13.asig.db.dao.domain.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    @Transactional
    public void save(User persoana) {
        repository.save(persoana);
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


}
