package ro.x13.asig.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.x13.asig.db.dao.domain.Auto;

import java.util.List;


public interface AutoRepository extends JpaRepository<Auto, Long> {

    List<Auto> findAllByOrderById();
/*
    Query query = entityManager.createQuery("From Foo");
    int pageNumber = 1;
    int pageSize = 10;
query.setFirstResult((pageNumber-1) * pageSize);
query.setMaxResults(pageSize);
    List <Foo> fooList = query.getResultList();
 */
}

