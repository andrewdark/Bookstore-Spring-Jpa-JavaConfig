package ua.pp.darknsoft.services;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Repository
public class BookServiceImpl {
    @PersistenceContext
    private EntityManager emf;

    @Transactional(readOnly = true)
    public List<Book> findAll() {

        List<Book> contacts = emf.createNamedQuery("Book.findAll", Book.class).getResultList();
        
        return contacts;
    }
}
