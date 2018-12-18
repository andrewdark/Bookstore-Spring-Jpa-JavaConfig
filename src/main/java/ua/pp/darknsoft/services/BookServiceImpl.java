package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.entities.Book;
import ua.pp.darknsoft.repositories.BookRepositoryImpl;
import ua.pp.darknsoft.repositories.specifications.book.BookSpecification;

import java.util.List;

@Service
@Transactional
@Repository
public class BookServiceImpl {

    @Autowired
    BookRepositoryImpl bookRepository;

    @Transactional(readOnly = true)
    public List<Book> findAll() {

        List<Book> contacts = bookRepository.query(new BookSpecification());
        //new ArrayList<>();//emf.createNamedQuery("Book.findAll", Book.class).getResultList();
        return contacts;
    }
}
