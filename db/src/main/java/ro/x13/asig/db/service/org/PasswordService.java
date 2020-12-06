package ro.x13.asig.db.service.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.PasswordRepository;
import ro.x13.asig.db.dao.domain.org.Password;
import ro.x13.asig.db.service.CrudService;

@Service
public class PasswordService extends CrudService<Password> {

    @Autowired
    private PasswordRepository repository;

    @Override
    public CrudRepository<Password, Long> getRepo() {
        return repository;
    }

    public Password load(Long id) {
        if (id == null){
            return new Password();
        }
        return repository.findById(id).get();
    }


}
